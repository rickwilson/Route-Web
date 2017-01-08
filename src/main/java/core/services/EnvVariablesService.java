package core.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EnvVariablesService {
    @Cacheable("getEnvName")
    public String getEnvName() {
        if(System.getProperty("ENV_NAME")!=null&&System.getProperty("ENV_NAME").trim().length()>0) {
            return System.getProperty("ENV_NAME");
        } else if(System.getenv("ENV_NAME")!=null&&System.getenv("ENV_NAME").trim().length()>0) {
            return System.getenv("ENV_NAME");
        } else {
            return "UNKNOWN ENV_NAME";
        }
    }

    @Cacheable("getBugsnagApiKey")
    public String getBugsnagApiKey() {
        if(System.getProperty("BUGSNAG_API_KEY")!=null&&System.getProperty("BUGSNAG_API_KEY").trim().length()>0) {
            return System.getProperty("BUGSNAG_API_KEY");
        } else if(System.getenv("BUGSNAG_API_KEY")!=null&&System.getenv("BUGSNAG_API_KEY").trim().length()>0) {
            return System.getenv("BUGSNAG_API_KEY");
        } else {
            return "UNKNOWN BUGSNAG_API_KEY";
        }
    }

    @Cacheable("getFromEmailAddress")
    public String getFromEmailAddress() {
        if(System.getProperty("FROM_EMAIL_ADDRESS")!=null&&System.getProperty("FROM_EMAIL_ADDRESS").trim().length()>0) {
            return System.getProperty("FROM_EMAIL_ADDRESS");
        } else if(System.getenv("FROM_EMAIL_ADDRESS")!=null&&System.getenv("FROM_EMAIL_ADDRESS").trim().length()>0) {
            return System.getenv("FROM_EMAIL_ADDRESS");
        } else {
            return "UNKNOWN FROM_EMAIL_ADDRESS";
        }
    }

    @Cacheable("getEmailSmtpUrl")
    public String getEmailSmtpUrl() {
        if(System.getProperty("EMAIL_SMTP_URL")!=null&&System.getProperty("EMAIL_SMTP_URL").trim().length()>0) {
            return System.getProperty("EMAIL_SMTP_URL");
        } else if(System.getenv("EMAIL_SMTP_URL")!=null&&System.getenv("EMAIL_SMTP_URL").trim().length()>0) {
            return System.getenv("EMAIL_SMTP_URL");
        } else {
            return "UNKNOWN EMAIL_SMTP_URL";
        }
    }

    @Cacheable("getEmailSmtpPassword")
    public String getEmailSmtpPassword() {
        if(System.getProperty("EMAIL_SMTP_PASSWORD")!=null&&System.getProperty("EMAIL_SMTP_PASSWORD").trim().length()>0) {
            return System.getProperty("EMAIL_SMTP_PASSWORD");
        } else if(System.getenv("EMAIL_SMTP_PASSWORD")!=null&&System.getenv("EMAIL_SMTP_PASSWORD").trim().length()>0) {
            return System.getenv("EMAIL_SMTP_PASSWORD");
        } else {
            return "UNKNOWN EMAIL_SMTP_PASSWORD";
        }
    }

    @Cacheable("getPlaidLinkEnvStage")
    public String getPlaidLinkEnvStage() {
        if(System.getProperty("PLAID_LINK_ENV_STAGE")!=null&&System.getProperty("PLAID_LINK_ENV_STAGE").trim().length()>0) {
            return System.getProperty("PLAID_LINK_ENV_STAGE");
        } else if(System.getenv("PLAID_LINK_ENV_STAGE")!=null&&System.getenv("PLAID_LINK_ENV_STAGE").trim().length()>0) {
            return System.getenv("PLAID_LINK_ENV_STAGE");
        } else {
            return "UNKNOWN PLAID_LINK_ENV_STAGE";
        }
    }

    @Cacheable("getPlaidPublicKey")
    public String getPlaidPublicKey() {
        if(System.getProperty("PLAID_PUBLIC_KEY")!=null&&System.getProperty("PLAID_PUBLIC_KEY").trim().length()>0) {
            return System.getProperty("PLAID_PUBLIC_KEY");
        } else if(System.getenv("PLAID_PUBLIC_KEY")!=null&&System.getenv("PLAID_PUBLIC_KEY").trim().length()>0) {
            return System.getenv("PLAID_PUBLIC_KEY");
        } else {
            return "UNKNOWN PLAID_PUBLIC_KEY";
        }
    }

    @Cacheable("getPlaidClientId")
    public String getPlaidClientId() {
        if(System.getProperty("PLAID_CLIENT_ID")!=null&&System.getProperty("PLAID_CLIENT_ID").trim().length()>0) {
            return System.getProperty("PLAID_CLIENT_ID");
        } else if(System.getenv("PLAID_CLIENT_ID")!=null&&System.getenv("PLAID_CLIENT_ID").trim().length()>0) {
            return System.getenv("PLAID_CLIENT_ID");
        } else {
            return "UNKNOWN PLAID_CLIENT_ID";
        }
    }

    @Cacheable("getPlaidSecret")
    public String getPlaidSecret() {
        if(System.getProperty("PLAID_SECRET")!=null&&System.getProperty("PLAID_SECRET").trim().length()>0) {
            return System.getProperty("PLAID_SECRET");
        } else if(System.getenv("PLAID_SECRET")!=null&&System.getenv("PLAID_SECRET").trim().length()>0) {
            return System.getenv("PLAID_SECRET");
        } else {
            return "UNKNOWN PLAID_SECRET";
        }
    }

    @Cacheable("getPlaidApiEndpoint")
    public String getPlaidApiEndpoint() {
        if(System.getProperty("PLAID_API_ENDPOINT")!=null&&System.getProperty("PLAID_API_ENDPOINT").trim().length()>0) {
            return System.getProperty("PLAID_API_ENDPOINT");
        } else if(System.getenv("PLAID_API_ENDPOINT")!=null&&System.getenv("PLAID_API_ENDPOINT").trim().length()>0) {
            return System.getenv("PLAID_API_ENDPOINT");
        } else {
            return "UNKNOWN PLAID_API_ENDPOINT";
        }
    }

    @Cacheable("getStripeApiKey")
    public String getStripeApiKey() {
        if(System.getProperty("STRIPE_API_KEY")!=null&&System.getProperty("STRIPE_API_KEY").trim().length()>0) {
            return System.getProperty("STRIPE_API_KEY");
        } else if(System.getenv("STRIPE_API_KEY")!=null&&System.getenv("STRIPE_API_KEY").trim().length()>0) {
            return System.getenv("STRIPE_API_KEY");
        } else {
            return "UNKNOWN STRIPE_API_KEY";
        }
    }

    @Cacheable("getRecaptchaSecret")
    public String getRecaptchaSecret() {
        if(System.getProperty("RECAPTCHA_SECRET")!=null&&System.getProperty("RECAPTCHA_SECRET").trim().length()>0) {
            return System.getProperty("RECAPTCHA_SECRET");
        } else if(System.getenv("RECAPTCHA_SECRET")!=null&&System.getenv("RECAPTCHA_SECRET").trim().length()>0) {
            return System.getenv("RECAPTCHA_SECRET");
        } else {
            return "UNKNOWN RECAPTCHA_SECRET";
        }
    }

    @Cacheable("getRecaptchaSiteKey")
    public String getRecaptchaSiteKey() {
        if(System.getProperty("RECAPTCHA_SITE_KEY")!=null&&System.getProperty("RECAPTCHA_SITE_KEY").trim().length()>0) {
            return System.getProperty("RECAPTCHA_SITE_KEY");
        } else if(System.getenv("RECAPTCHA_SITE_KEY")!=null&&System.getenv("RECAPTCHA_SITE_KEY").trim().length()>0) {
            return System.getenv("RECAPTCHA_SITE_KEY");
        } else {
            return "UNKNOWN RECAPTCHA_SITE_KEY";
        }
    }

    @Cacheable("getAlexaAccessKey")
    public String getAlexaAccessKey() {
        if(System.getProperty("ALEXA_ACCESS_KEY")!=null&&System.getProperty("ALEXA_ACCESS_KEY").trim().length()>0) {
            return System.getProperty("ALEXA_ACCESS_KEY");
        } else if(System.getenv("ALEXA_ACCESS_KEY")!=null&&System.getenv("ALEXA_ACCESS_KEY").trim().length()>0) {
            return System.getenv("ALEXA_ACCESS_KEY");
        } else {
            return "UNKNOWN ALEXA_ACCESS_KEY";
        }
    }

    @Cacheable("getAlexaSecret")
    public String getAlexaSecret() {
        if(System.getProperty("ALEXA_SECRET")!=null&&System.getProperty("ALEXA_SECRET").trim().length()>0) {
            return System.getProperty("ALEXA_SECRET");
        } else if(System.getenv("ALEXA_SECRET")!=null&&System.getenv("ALEXA_SECRET").trim().length()>0) {
            return System.getenv("ALEXA_SECRET");
        } else {
            return "UNKNOWN ALEXA_SECRET";
        }
    }

    @Cacheable("getAlexaSite")
    public String getAlexaSite() {
        if(System.getProperty("ALEXA_SITE")!=null&&System.getProperty("ALEXA_SITE").trim().length()>0) {
            return System.getProperty("ALEXA_SITE");
        } else if(System.getenv("ALEXA_SITE")!=null&&System.getenv("ALEXA_SITE").trim().length()>0) {
            return System.getenv("ALEXA_SITE");
        } else {
            return "UNKNOWN ALEXA_SITE";
        }
    }
    
    @Cacheable("getAdminEmailAddress")
    public String getAdminEmailAddress() {
        if(System.getProperty("ADMIN_EMAIL_ADDRESS")!=null&&System.getProperty("ADMIN_EMAIL_ADDRESS").trim().length()>0) {
            return System.getProperty("ADMIN_EMAIL_ADDRESS");
        } else if(System.getenv("ADMIN_EMAIL_ADDRESS")!=null&&System.getenv("ADMIN_EMAIL_ADDRESS").trim().length()>0) {
            return System.getenv("ADMIN_EMAIL_ADDRESS");
        } else {
            return "UNKNOWN ADMIN_EMAIL_ADDRESS";
        }
    }
}
