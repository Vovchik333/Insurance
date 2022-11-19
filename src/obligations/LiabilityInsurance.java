package obligations;

public class LiabilityInsurance extends Contract{
    public LiabilityInsurance(int contract_id, String name_organization, String address_organization,
                              String surname_client, String first_name_client, String mid_name_client,
                              String address_client, int passport_id, int validity, String currency,
                              String insure_event, String insure_object, double payment_amount,
                              double contributions_client, double riskLvl, String type_contract,
                              int derivative_id) {
        super(contract_id, name_organization, address_organization, surname_client, first_name_client,
                mid_name_client, address_client, passport_id, validity, currency, insure_event,
                insure_object, payment_amount, contributions_client, riskLvl, type_contract,derivative_id);
    }
}
