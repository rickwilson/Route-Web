package core.emails;

public class RouteDefaultTemplate {

    public static String createBody(String title, String content, String asterisk, String actionUrl, String actionLabel) {
        if(asterisk != null && asterisk.trim().length() > 0) {
            asterisk = "                                    <p><small>"+asterisk+"</small></p>\n";
        } else {
            asterisk = "";
        }
        String actionBlock = "";
        if(actionUrl != null && actionUrl.trim().length() > 0) {
            actionBlock = "                            <!-- button -->\n" +
                    "                            <tr>\n" +
                    "                                <td>\n" +
                    "                                    <table height=\"30\" align=\"left\" valign=\"middle\" border=\"0\" cellpadding=\"0\"\n" +
                    "                                           cellspacing=\"0\" class=\"tablet-button\" st-button=\"read-more-button\"\n" +
                    "                                           bgcolor=\"#FFF\"\n" +
                    "                                           style=\"background-color:#FFF; background-clip: padding-box;font-size:13px; font-family:'Abel', Arial, Helvetica, sans-serif; text-align:center;  color:#ffffff; font-weight: 300; padding-left:18px; padding-right:18px; border: 2px solid #1fb6c6;\">\n" +
                    "                                        <tbody>\n" +
                    "                                        <tr>\n" +
                    "                                            <td width=\"auto\" align=\"center\" valign=\"middle\" height=\"30\"\n" +
                    "                                                st-title=\"read-more-button\"\n" +
                    "                                                style=\"padding-left:18px; padding-right:18px;font-family:'Abel', Arial, Helvetica, sans-serif; text-align:center;  color:#ffffff; font-weight: 300;\">\n" +
                    "                                        <span style=\"color: #1fb6c6; font-weight: 300;\">\n" +
                    "                                        <a style=\"color: #1fb6c6; text-align:center;text-decoration: none;\" href=\""+actionUrl+"\" st-content=\"read-more-button\">"+actionLabel+"</a>\n" +
                    "                                        </span>\n" +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                        </tbody>\n" +
                    "                                    </table>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                            <!-- /button -->\n";
        }

        String body = "<html>\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Route</title>\n" +
                "    <style type=\"text/css\">\n" +
                "        /* Client-specific Styles */\n" +
                "        #outlook a {\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "\n" +
                "        /* Force Outlook to provide a \"view in browser\" menu link. */\n" +
                "        body {\n" +
                "            width: 100% !important;\n" +
                "            -webkit-text-size-adjust: 100%;\n" +
                "            -ms-text-size-adjust: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "\n" +
                "        /* Prevent Webkit and Windows Mobile platforms from changing default font sizes, while not breaking desktop design. */\n" +
                "        .ExternalClass {\n" +
                "            width: 100%;\n" +
                "        }\n" +
                "\n" +
                "        /* Force Hotmail to display emails at full width */\n" +
                "        .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {\n" +
                "            line-height: 100%;\n" +
                "        }\n" +
                "\n" +
                "        /* Force Hotmail to display normal line spacing.  More on that: http://www.emailonacid.com/forum/viewthread/43/ */\n" +
                "        #backgroundTable {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            width: 100% !important;\n" +
                "            line-height: 100% !important;\n" +
                "        }\n" +
                "\n" +
                "        img {\n" +
                "            outline: none;\n" +
                "            text-decoration: none;\n" +
                "            border: none;\n" +
                "            -ms-interpolation-mode: bicubic;\n" +
                "        }\n" +
                "\n" +
                "        a img {\n" +
                "            border: none;\n" +
                "        }\n" +
                "\n" +
                "        .image_fix {\n" +
                "            display: block;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            margin: 0px 0px !important;\n" +
                "        }\n" +
                "\n" +
                "        table td {\n" +
                "            border-collapse: collapse;\n" +
                "        }\n" +
                "\n" +
                "        table {\n" +
                "            border-collapse: collapse;\n" +
                "            mso-table-lspace: 0pt;\n" +
                "            mso-table-rspace: 0pt;\n" +
                "        }\n" +
                "\n" +
                "        a { /*color: #e95353;*/\n" +
                "            text-underline: none !important;\n" +
                "            text-decoration: none;\n" +
                "            text-decoration: none !important;\n" +
                "        }\n" +
                "\n" +
                "        /*STYLES*/\n" +
                "        table[class=full] {\n" +
                "            width: 100%;\n" +
                "            clear: both;\n" +
                "        }\n" +
                "\n" +
                "        /*################################################*/\n" +
                "        /*IPAD STYLES*/\n" +
                "        /*################################################*/\n" +
                "        @media only screen and (max-width: 640px) {\n" +
                "            a[href^=\"tel\"], a[href^=\"sms\"] {\n" +
                "                text-decoration: none;\n" +
                "                color: #ffffff; /* or whatever your want */\n" +
                "                pointer-events: none;\n" +
                "                cursor: default;\n" +
                "            }\n" +
                "\n" +
                "            .mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {\n" +
                "                text-decoration: default;\n" +
                "                color: #ffffff !important;\n" +
                "                pointer-events: auto;\n" +
                "                cursor: default;\n" +
                "            }\n" +
                "\n" +
                "            table[class=devicewidth] {\n" +
                "                width: 440px !important;\n" +
                "                text-align: center !important;\n" +
                "            }\n" +
                "\n" +
                "            table[class=devicewidthinner] {\n" +
                "                width: 420px !important;\n" +
                "                text-align: center !important;\n" +
                "            }\n" +
                "\n" +
                "            table[class=\"sthide\"] {\n" +
                "                display: none !important;\n" +
                "            }\n" +
                "\n" +
                "            img[class=\"bigimage\"] {\n" +
                "                width: 420px !important;\n" +
                "                height: 219px !important;\n" +
                "            }\n" +
                "\n" +
                "            img[class=\"col2img\"] {\n" +
                "                width: 420px !important;\n" +
                "                height: 258px !important;\n" +
                "            }\n" +
                "\n" +
                "            img[class=\"image-banner\"] {\n" +
                "                width: 440px !important;\n" +
                "                height: 106px !important;\n" +
                "            }\n" +
                "\n" +
                "            td[class=\"menu\"] {\n" +
                "                text-align: center !important;\n" +
                "                padding: 0 0 10px 0 !important;\n" +
                "            }\n" +
                "\n" +
                "            td[class=\"logo\"] {\n" +
                "                padding: 10px 0 5px 0 !important;\n" +
                "                margin: 0 auto !important;\n" +
                "            }\n" +
                "\n" +
                "            img[class=\"logo\"] {\n" +
                "                padding: 0 !important;\n" +
                "                margin: 0 auto !important;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        /*##############################################*/\n" +
                "        /*IPHONE STYLES*/\n" +
                "        /*##############################################*/\n" +
                "        @media only screen and (max-width: 480px) {\n" +
                "            a[href^=\"tel\"], a[href^=\"sms\"] {\n" +
                "                text-decoration: none;\n" +
                "                color: #ffffff; /* or whatever your want */\n" +
                "                pointer-events: none;\n" +
                "                cursor: default;\n" +
                "            }\n" +
                "\n" +
                "            .mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {\n" +
                "                text-decoration: default;\n" +
                "                color: #ffffff !important;\n" +
                "                pointer-events: auto;\n" +
                "                cursor: default;\n" +
                "            }\n" +
                "\n" +
                "            table[class=devicewidth] {\n" +
                "                width: 280px !important;\n" +
                "                text-align: center !important;\n" +
                "            }\n" +
                "\n" +
                "            table[class=devicewidthinner] {\n" +
                "                width: 260px !important;\n" +
                "                text-align: center !important;\n" +
                "            }\n" +
                "\n" +
                "            table[class=\"sthide\"] {\n" +
                "                display: none !important;\n" +
                "            }\n" +
                "\n" +
                "            img[class=\"bigimage\"] {\n" +
                "                width: 260px !important;\n" +
                "                height: 136px !important;\n" +
                "            }\n" +
                "\n" +
                "            img[class=\"col2img\"] {\n" +
                "                width: 260px !important;\n" +
                "                height: 160px !important;\n" +
                "            }\n" +
                "\n" +
                "            img[class=\"image-banner\"] {\n" +
                "                width: 280px !important;\n" +
                "                height: 68px !important;\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<!-- start of header -->\n" +
                "<table width=\"100%\" bgcolor=\"#FFF\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" st-sortable=\"header\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <table width=\"100%\" bgcolor=\"#000000\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\"\n" +
                "                   class=\"devicewidth\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <!-- logo -->\n" +
                "                        <table width=\"540\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\"\n" +
                "                               class=\"devicewidth\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td valign=\"middle\" width=\"270\" style=\"padding: 10px 0 10px 0px;\" class=\"logo\">\n" +
                "                                    <a href=\"https://routeit.cloud\">\n" +
                "                                        <img src=\"https://d1pv9ulu41r3n5.cloudfront.net/img/email/route-logo-white-on-black-email.jpg\"\n" +
                "                                             width=\"200\" alt=\"logo\" border=\"0\"\n" +
                "                                             style=\"display:block; border:none; outline:none; text-decoration:none;\"\n" +
                "                                             st-image=\"edit\" class=\"logo\"></a>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table>\n" +
                "<!-- end of header -->\n" +
                "<p></p>\n" +
                "<p>\n" +
                "    <!-- image + text -->\n" +
                "</p>\n" +
                "<table width=\"100%\" bgcolor=\"#FFF\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" st-sortable=\"bigimage\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <table bgcolor=\"#ffffff\" width=\"700\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"\n" +
                "                   class=\"devicewidth\" modulebg=\"edit\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td width=\"100%\" height=\"20\"></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table width=\"540\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"\n" +
                "                               class=\"devicewidthinner\">\n" +
                "                            <tbody>\n" +
                "                            <tr><!-- Spacing -->\n" +
                "                            <tr>\n" +
                "                                <td width=\"100%\" height=\"20\"></td>\n" +
                "                            </tr>\n" +
                "                            <!-- Spacing --><!-- title -->\n" +
                "                            <tr>\n" +
                "                                <td style=\"font-family: 'Abel', Arial, Helvetica, sans-serif; font-size: 18px; color: #333333; text-align:left;line-height: 20px;\"\n" +
                "                                    st-title=\"rightimage-title\">\n" +
                "                                    "+title+"\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            <!-- end of title --><!-- Spacing -->\n" +
                "                            <tr>\n" +
                "                                <td width=\"100%\" height=\"20\"></td>\n" +
                "                            </tr>\n" +
                "                            <!-- Spacing --><!-- content -->\n" +
                "                            <tr>\n" +
                "                                <td style=\"font-family: 'Abel', Arial, Helvetica, sans-serif; font-size: 13px; color: #95a5a6; text-align:left;line-height: 24px;\"\n" +
                "                                    st-content=\"rightimage-paragraph\">\n" +
                "                                    <p>"+content+"</p>\n" +
                asterisk+
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            <!-- end of content --><!-- Spacing -->\n" +
                "                            <tr>\n" +
                "                                <td width=\"100%\" height=\"10\"></td>\n" +
                "                            </tr>\n" +
                actionBlock+
                "                            <!-- Spacing -->\n" +
                "                            <tr>\n" +
                "                                <td width=\"100%\" height=\"20\"></td>\n" +
                "                            </tr>\n" +
                "                            <!-- Spacing -->\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table>\n" +
                "<p></p>\n" +
                "<p>\n" +
                "    <!-- start of header -->\n" +
                "</p>\n" +
                "<table width=\"100%\" bgcolor=\"#FFF\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" st-sortable=\"header\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <table width=\"100%\" bgcolor=\"#000000\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\"\n" +
                "                   class=\"devicewidth\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <!-- logo -->\n" +
                "                        <table width=\"540\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\"\n" +
                "                               class=\"devicewidth\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td valign=\"middle\" width=\"270\" style=\"padding: 10px 0 10px 0px;\" class=\"logo\">\n" +
                "                                    &nbsp;\n" +
                "                                </td>\n" +
                "                                <td colspan=\"3\" align=\"right\">\n" +
                "                                    <p style=\"color:#FFF;font-family: 'Abel', Arial, Helvetica, sans-serif;font-size:10px;\">\n" +
                "                                        Copyright 2016 Route | All Rights Reserved</p>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table>\n" +
                "<p>\n" +
                "    <!-- start textbox-with-title -->\n" +
                "\n" +
                "    <!-- Start of preheader -->\n" +
                "</p>\n" +
                "<table width=\"100%\" bgcolor=\"#FFF\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" st-sortable=\"postfooter\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td width=\"100%\">\n" +
                "            <table width=\"700\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\">\n" +
                "                <tbody>\n" +
                "                <!-- Spacing -->\n" +
                "                <tr>\n" +
                "                    <td width=\"100%\" height=\"5\"></td>\n" +
                "                </tr>\n" +
                "                <!-- Spacing -->\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" valign=\"middle\"\n" +
                "                        style=\"font-family: 'Abel', Arial, Helvetica, sans-serif; font-size: 10px;color: #999999\"\n" +
                "                        st-content=\"preheader\">\n" +
                "\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <!-- Spacing -->\n" +
                "                <tr>\n" +
                "                    <td width=\"100%\" height=\"5\"></td>\n" +
                "                </tr>\n" +
                "                <!-- Spacing -->\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table>\n" +
                "<!-- End of preheader -->\n" +
                "<p></p>\n" +
                "</body>\n" +
                "</html>\n";
        return body;
    }
}
