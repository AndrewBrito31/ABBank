package bankmanagement; //2.1 JSON file of flows

import com.google.gson.*;

import components.Credit;
import components.Debit;
import components.Flow;
import components.Transfer;

import java.lang.reflect.Type;

public class FlowDeserializer implements JsonDeserializer<Flow> {

    @Override
    public Flow deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        if (jsonObject.has("issuingAccountNumber")) {
            return context.deserialize(json, Transfer.class);
        } else if (jsonObject.has("amount")) {
            if (jsonObject.get("amount").getAsDouble() > 0) {
                return context.deserialize(json, Credit.class);
            } else {
                return context.deserialize(json, Debit.class);
            }
        }

        throw new JsonParseException("Unknown flow type encountered!");
    }
}