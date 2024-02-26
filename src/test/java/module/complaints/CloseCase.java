package module.complaints;

import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import page.objects.complaints.CloseComplaint_Page;
import utility.ConfigManager;
import utility.WebDr;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CloseCase extends WebDr {

    public ConfigManager Config = ConfigManager.getConfigManagerInstance();
    public String CauseOfProblem = Config.getKeyValue("CauseOfProblem");
    public String SummaryOfIssue = Config.getKeyValue("SummaryOfIssue");
    public String File = Config.getKeyValue("File3");
    public String sCategory = null;
    public String ResolutionType = null;
    public String RefundDate = null;
    /*public String Controllability = null;
    public String TCFOutcomes = null;
    public String RegulatoryDecision = null;
    public String ResolutionSubType = null;
    public String SecondaryComplaint = null;
    public String Escalation = null;
    public String FinalClaim = null;
    public String OriginalClaim = null;
    public String ResolutionSub = null;
    public String PaymentType = null;
    public String Unresolved = null;*/

    public CloseCase(WebDriver wdriver, ExtentTest test) {
        this.wdriver = wdriver;
        this.test = test;
    }

    public void Execute() throws Exception {
        try {

            sCategory = getValue("Category");
            ResolutionType = getValue("Resolution");
            RefundDate = formattedDate("dd MMM yyyy");
            /*Controllability = getValue("Controllability");
            SecondaryComplaint = getValue("SecondaryComplaint");
            Escalation = getValue("EscalationControl");
            FinalClaim = getValue("FinalClaim");
            OriginalClaim = getValue("OriginalClaim");
            TCFOutcomes = getValue("TCFOutcomes");
            ResolutionSub = getValue("ResolutionSub");
            PaymentType = getValue("PaymentType");
            Unresolved = getValue("RejectReason");
            RegulatoryDecision = getValue("RegulatoryDecision");*/

            CloseComplaint_Page.setUp();

            System.out.println("**********************Complaints Closure************************");

            JSClick("tabActions", "Click on Actions Tab by JSClick");
            Thread.sleep(2000);
            JSClick("linkCloseCase", "Click on the Close Case Link");

            //=================Common closure fields to be entered===========
            setText("txtCauseOfProblem", CauseOfProblem, "enter on cause of problem");
            setText("txtSummaryOfIssue", SummaryOfIssue, "Enter the summary of the issue ");
            select("drpResolutionType", "value", ResolutionType, "Select the resolution type");

            CasesCreation caseHandle = new CasesCreation(wdriver, test);

            //================Controllability===============
            JSClick("drpControllability", "Click on controllabity dropdown");
            caseHandle.pickRandomOption("Controllability");
            /*WebDr.page_Objects.put("selControllability", "XPATH|//label[contains(text(),'Controllability')]/following-sibling::div//lightning-base-combobox-item[@data-value='" + Controllability + "']");
            JSClick("selControllability", "Select the controllability");*/

            //================Controllable Secondary Complaint Type===============
            JSClick("drpSecondaryComplaint", "Click on Secondary Complaint dropdown");
            caseHandle.pickRandomOption("Controllable Secondary Complaint Type");
            /*WebDr.page_Objects.put("selSecondaryComplaint", "XPATH|//label[contains(text(),'Secondary')]/following-sibling::div//lightning-base-combobox-item[@data-value='" + SecondaryComplaint + "']");
            JSClick("selSecondaryComplaint", "Select the Secondary Complaint");*/

            //================Escalation Controllable===============
            JSClick("drpEscalationControl", "Click on Escalation Control dropdown");
            caseHandle.pickRandomOption("Escalation Controllable");
            /*WebDr.page_Objects.put("selEscalationControl", "XPATH|//label[contains(text(),'Escalation')]/following-sibling::div//lightning-base-combobox-item[@data-value='" + Escalation + "']");
            JSClick("selEscalationControl", "Select the Escalation Control");*/

            //================Final Claims Decision===============
            JSClick("drpFinalClaims", "Click on FinalClaims dropdown");
            caseHandle.pickRandomOption("Final Claims Decision");
            /*WebDr.page_Objects.put("selFinalClaims", "XPATH|//label[contains(text(),'Final')]/following-sibling::div//lightning-base-combobox-item[@data-value='" + FinalClaim + "']");
            JSClick("selFinalClaims", "Select the Final Claims");*/

            //================Original Claims Decision===============
            JSClick("drpOriginalClaims", "Click on Original Claims dropdown");
            caseHandle.pickRandomOption("Original Claims Decision");
            /*WebDr.page_Objects.put("selOriginalClaims", "XPATH|//label[contains(text(),'Original')]/following-sibling::div//lightning-base-combobox-item[@data-value='" + OriginalClaim + "']");
            JSClick("selOriginalClaims", "Select the Original Claims");*/

            //================TCF Outcomes===============
            JSClick("drpTCFOutcomes", "Click on TCF outcomes");
            caseHandle.pickRandomOption("TCF Outcomes");
            /*WebDr.page_Objects.put("selTCFOutcomes", "XPATH|//span[text()='" + TCFOutcomes + "']");
            JSClick("selTCFOutcomes", "Select the TCF outcomes");*/

            //================TCF Outcomes===============
            if (sCategory.equals("Level 3")) {
                JSClick("drpRegulatoryDecision", "Click on Regulatory Decision");
                caseHandle.pickRandomOption("Regulatory Decision");
                /*WebDr.page_Objects.put("selRegulatory", "XPATH|//span[text()='" + RegulatoryDecision + "']");
                JSClick("selRegulatory", "Select the Regulatory Decision");*/
            }

            //==============Specific closure fields to be entered================
            if (ResolutionType.equals("Resolved Monetary")) {

                JSClick("drpResolutionSubType", "Click on Resolution Sub Type");
                caseHandle.pickRandomOption("Resolution Sub Type");
                /*WebDr.page_Objects.put("selResolutionSub", "XPATH|//span[text()='" + ResolutionSub + "']");
                JSClick("selResolutionSub", "Select the Resolution Sub Type");*/

                JSClick("drpPaymentMethod", "Click on Payment Method");
                caseHandle.pickRandomOption("Payment Method");
                /*WebDr.page_Objects.put("selPaymentMethod", "XPATH|//span[text()='" + PaymentType + "']");
                JSClick("selPaymentMethod", "Select the Payment Method");*/

                setText("txtAmtRefundDate", RefundDate, "Enter the Payment Date");

            } else if (ResolutionType.equals("Rejected")) {

                JSClick("drpRejectedReason", "Click on Unresolved Reason");
                caseHandle.pickRandomOption("Unresolved Reason");
                /*WebDr.page_Objects.put("selRejectedReason", "XPATH|//span[text()='" + Unresolved + "']");
                JSClick("selRejectedReason", "Select the Unresolved Reason");*/
            }


            //=============Document Upload Logic===============
            if (sCategory.equals("Level 1") || sCategory.equals("Level 2")) {
                if (ResolutionType.equals("Rejected")) {
                    uploadDoc("Repudiation Review");
                    uploadDoc("Repudiation Letter");
                } else if (ResolutionType.equals("Resolved Non-Monetary")) {
                    uploadDoc("Confirmation of Resolution");
                } else {
                    uploadDoc("CRC resolution outcome (CRC Deadlock letter)");
                    uploadDoc("Committee Decision");
                    uploadDoc("Confirmation of Settlement");
                    uploadDoc("Signed Accepted Settlement");
                    uploadDoc("Proof of Payment");
                }
            }

            if (sCategory.equals("Level 3")) {
                uploadDoc("Feedback from Ombud");
                if (ResolutionType.equals("Rejected")) {
                    uploadDoc("Repudiation Review");
                    uploadDoc("Repudiation Letter");
                } else if (ResolutionType.equals("Resolved Non-Monetary")) {
                    uploadDoc("Confirmation of Resolution");
                } else {
                    uploadDoc("Response Letter");
                    uploadDoc("Confirmation of resolution from Ombudsman");
                    uploadDoc("Confirmation of Settlement");
                }
            }

            Thread.sleep(5000);
            JSClick("btnClose", "Click the Close button");
            //handlePageLoading("//div[@class='slds-spinner slds-spinner_brand slds-spinner_large']");

            if (getText("popUpMessage", "Get Popup Message").contains("Closed")) {
                verifyText("popUpMessage", true, "Closed", "Verify Case Closed");
            } else {
                verifyText("popUpMessage", true, "server", "Verify Case Closed with Error");
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public String formattedDate(String format) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }

    public void uploadDoc(String docName) throws Exception {
        Thread.sleep(3000);
        JSClick("drpDocType", "Click on the Document type dropdown");
        WebDr.page_Objects.put("selDocumentCOR", "XPATH|//span[text()='" + docName + "']");
        JSClick("selDocumentCOR", "" + docName + " is selected");
		/*JSClick("btnUploadFiles","Click Upload Button");
		robotUploadFile(File, "upload the file");
		Thread.sleep(6000);*/
        uploadFile("btnUploadFiles", File, "upload the file");
        clkDone();
    }

    public void clkDone() throws Exception {
        String FinalColor = getElement("btnDone").getCssValue("background-color");
        //System.out.println(FinalColor);
        for (int i = 1; i <= 60; i++) {
            if (FinalColor.equals("rgba(161, 50, 94, 1)") || FinalColor.equals("rgba(122, 36, 70, 1)")) {
                JSClick("btnDone", "click on Done button");
                break;
            } else {
                Thread.sleep(1000);
                FinalColor = getElement("btnDone").getCssValue("background-color");
                //System.out.println(FinalColor);
                continue;
            }

        }
    }

}


