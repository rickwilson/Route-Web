package core.services;

import core.entities.ShippingTracking;
import core.entities.ShippingTrackingDAO;
import core.entities.client.ClientDashboardObject;
import core.thirdParty.aftership.enums.ISO3Country;
import core.thirdParty.aftership.enums.StatusTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class ShippingTrackingService {
    private final ShippingTrackingDAO shippingTrackingDAO;
    private final TransactionService transactionService;
    private final IsoCountryService isoCountryService;

    @Autowired
    public ShippingTrackingService(ShippingTrackingDAO shippingTrackingDAO,
                                   TransactionService transactionService,
                                   IsoCountryService isoCountryService) {
        Assert.notNull(shippingTrackingDAO, "ShippingTrackingDAO must not be null!");
        Assert.notNull(transactionService, "TransactionService must not be null!");
        Assert.notNull(isoCountryService, "IsoCountryService must not be null!");
        this.shippingTrackingDAO = shippingTrackingDAO;
        this.transactionService = transactionService;
        this.isoCountryService = isoCountryService;
    }

    public void populateClientDashboard(ClientDashboardObject clientDashboardObject) {
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        LocalDate monthAgoLocalDate = today.minusMonths(1);
        Timestamp monthAgo = Timestamp.valueOf(monthAgoLocalDate.atStartOfDay());
        int totalDeliveryDaysDay = 0;
        int totalDeliveriesDay = 0;
        int totalDeliveryDaysMonth = 0;
        int totalDeliveriesMonth = 0;
        int totalDeliveryDaysYear = 0;
        int totalDeliveriesYear = 0;
        HashMap<String,Integer> shipmentsPerCountry = new HashMap<>();
        for(ShippingTracking shippingTracking : shippingTrackingDAO.findByAccountIdAndCreatedAfter(clientDashboardObject.getAccountId(),monthAgo)) {
            LocalDate shipCreatedDate =  shippingTracking.getCreated().toLocalDateTime().toLocalDate();
            int shippingDays = shippingTracking.getDeliveryTime();
            if(shippingDays > 0) {
                if(shipCreatedDate.equals(today)) {
                    totalDeliveriesDay++;
                    totalDeliveryDaysDay += shippingDays;
                }
                if(shipCreatedDate.getMonthValue() == month) {
                    totalDeliveriesMonth++;
                    totalDeliveryDaysMonth += shippingDays;
                }
                totalDeliveriesYear++;
                totalDeliveryDaysYear += shippingDays;
            }
            StatusTag tag = shippingTracking.getTag();
            if(tag != null) {
                clientDashboardObject.addTotalShips();
                if(tag.equals(StatusTag.Delivered)) {
                    clientDashboardObject.addTotalDelivered();
                } else if(tag.equals(StatusTag.OutForDelivery) || tag.equals(StatusTag.InTransit)) {
                    clientDashboardObject.addTotalInTransit();
                } else if(tag.equals(StatusTag.InfoReceived) || tag.equals(StatusTag.Pending)) {
                    clientDashboardObject.addTotalReady();
                } else if(tag.equals(StatusTag.AttemptFail) || tag.equals(StatusTag.Exception) || tag.equals(StatusTag.Expired)) {
                    clientDashboardObject.addTotalFailed();
                }
            }
            if(shippingTracking.getDestinationCountryISO3() != null) {
                updateShipmentsPerCountry(shipmentsPerCountry,isoCountryService.getISO2CountryCode(shippingTracking.getDestinationCountryISO3().toString()));
            } else {
                updateShipmentsPerCountry(shipmentsPerCountry,isoCountryService.getISO2CountryCode(transactionService.getCustomerCountry(shippingTracking.getTransactionId())));
            }
        }
        if(totalDeliveryDaysDay < 1 || totalDeliveriesDay < 1) {
            clientDashboardObject.setAverageDeliveryDaysToday(0);
        } else {
            clientDashboardObject.setAverageDeliveryDaysToday(totalDeliveryDaysDay/totalDeliveriesDay);
        }
        if(totalDeliveryDaysMonth < 1 || totalDeliveriesMonth < 1) {
            clientDashboardObject.setAverageDeliveryDaysToday(0);
        } else {
            clientDashboardObject.setAverageDeliveryDaysMonth(totalDeliveryDaysMonth/totalDeliveriesMonth);
        }
        if(totalDeliveryDaysYear < 1 || totalDeliveriesYear < 1) {
            clientDashboardObject.setAverageDeliveryDaysToday(0);
        } else {
            clientDashboardObject.setAverageDeliveryDaysYear(totalDeliveryDaysYear/totalDeliveriesYear);
        }
        clientDashboardObject.setShipmentsPerCountry(shipmentsPerCountry);
    }

    private void updateShipmentsPerCountry(HashMap<String,Integer> shipmentsPerCountry,String destinationCountryISO3) {
        if(shipmentsPerCountry.containsKey(destinationCountryISO3)) {
            shipmentsPerCountry.put(destinationCountryISO3, shipmentsPerCountry.get(destinationCountryISO3) + 1);
        } else {
            shipmentsPerCountry.put(destinationCountryISO3,1);
        }
    }
}
