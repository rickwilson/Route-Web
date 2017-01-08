package core.util;

import core.thirdParty.aftership.enums.StatusTag;

public class ShipConverter {

    public static String convertStatusTag(StatusTag tag) {
        String converted = "Unknown";
        if(tag != null) {
            if(tag.equals(StatusTag.Delivered)) {
                converted = "Delivered";
            } else if(tag.equals(StatusTag.OutForDelivery) || tag.equals(StatusTag.InTransit)) {
                converted = "In Transit";
            } else if(tag.equals(StatusTag.InfoReceived) || tag.equals(StatusTag.Pending)) {
                converted = "Ready";
            } else if(tag.equals(StatusTag.AttemptFail) || tag.equals(StatusTag.Exception) || tag.equals(StatusTag.Expired)) {
                converted = "Exception";
            }
        }
        return converted;
    }
}
