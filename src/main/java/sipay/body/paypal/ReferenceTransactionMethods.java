package sipay.body.paypal;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

public class ReferenceTransactionMethods {
    @Getter
    @Setter
    private String order;

    @Getter
    @Setter
    private String reconciliation;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String logo;

    @Getter
    @Setter
    private String customId;

    @Getter
    @Setter
    private JSONObject notify;

    @Getter
    @Setter
    private JSONObject billing;

    @Getter
    @Setter
    private JSONObject policyData;

    public ReferenceTransactionMethods(JSONObject payload) {
        this.order = ((String) payload.opt("order"));
        this.reconciliation = ((String) payload.opt("reconciliation"));
        this.title = ((String) payload.opt("title"));
        this.logo = ((String) payload.opt("logo"));
        this.customId = ((String) payload.opt("customId"));
        this.notify = ((JSONObject) payload.opt("notify"));
        this.billing = ((JSONObject) payload.opt("billing"));
        this.policyData = ((JSONObject) payload.opt("policyData"));
    }

    public JSONObject update(JSONObject payload) {
        payload.put("order", this.order);
        payload.put("reconciliation", this.reconciliation);
        payload.put("title", this.title);
        payload.put("logo", this.logo);
        payload.put("custom_id", this.customId);
        payload.put("notify", this.notify);
        payload.put("billing", this.billing);
        payload.put("policy_data", this.policyData);

        return payload;
    }
}