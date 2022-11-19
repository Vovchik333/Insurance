package obligations;

import work_with_DB.ConnectorDB;

public abstract class Contract {
    private int contract_id;
    private String name_organization;
    private String address_organization;
    private String surname_client;
    private String first_name_client;
    private String mid_name_client;
    private String address_client;
    private int passport_id;
    private int validity;
    private String currency;
    private String insure_event;
    private String insure_object;
    private double payment_amount;
    private double contributions_client;
    private double riskLvl;
    private String type_contract;
    private int derivative_id;

    Contract(int contract_id, String name_organization, String address_organization,
             String surname_client, String first_name_client, String mid_name_client,
             String address_client, int passport_id, int validity, String currency,
             String insure_event, String insure_object, double payment_amount,
             double contributions_client, double riskLvl, String type_contract,
             int derivative_id){
        this.contract_id = contract_id;
        this.name_organization = name_organization;
        this.address_organization = address_organization;
        this.surname_client = surname_client;
        this.first_name_client = first_name_client;
        this.mid_name_client = mid_name_client;
        this.address_client = address_client;
        this.passport_id = passport_id;
        this.validity = validity;
        this.currency = currency;
        this.insure_event = insure_event;
        this.insure_object = insure_object;
        this.payment_amount = payment_amount;
        this.contributions_client = contributions_client;
        this.riskLvl = riskLvl;
        this.type_contract = type_contract;
        this.derivative_id = derivative_id;
    }

    public int getContract_id() {
        return contract_id;
    }

    public String getName_organization() {
        return name_organization;
    }

    public String getAddress_organization() {
        return address_organization;
    }

    public String getSurname_client() {
        return surname_client;
    }

    public String getFirst_name_client() {
        return first_name_client;
    }

    public String getMid_name_client() {
        return mid_name_client;
    }

    public String getAddress_client() {
        return address_client;
    }

    public int getValidity() {
        return validity;
    }

    public String getCurrency() {
        return currency;
    }

    public int getPassport_id() {
        return passport_id;
    }

    public double getPayment_amount() {
        return payment_amount;
    }

    public double getContributions_client() {
        return contributions_client;
    }

    public double getRiskLvl() {
        return riskLvl;
    }

    public String getInsure_event() {
        return insure_event;
    }

    public String getType_contract() {
        return type_contract;
    }

    public String getInsure_object() {
        return insure_object;
    }

    public int getDerivative_id() {
        return derivative_id;
    }

    public static void insertContract(Contract contract){
        ConnectorDB.updateTable("INSERT INTO Obligations(contract_id, name_org, address_org, " +
                "surname_client, first_name_client, mid_name_client, address_client, " +
                "passport_id, date_start, validity, currency, insure_event, insure_obj, payment_amount, " +
                "contributions_client, risk_lvl, type_contract, derivative_id)" +
                "VALUES (" + String.format("%d, '%s', '%s', '%s', '%s', '%s', '%s', %d, CURRENT_DATE(), " +
                        "%d, '%s', '%s', '%s', " + contract.getPayment_amount() + ", " + contract.getContributions_client()
                        + ", " + contract.getRiskLvl() + ", '%s', %d)", contract.getContract_id(),
                contract.getName_organization(), contract.getAddress_organization(), contract.getSurname_client(),
                contract.getFirst_name_client(), contract.getMid_name_client(), contract.getAddress_client(),
                contract.getPassport_id(), contract.getValidity(), contract.getCurrency(), contract.getInsure_event(),
                contract.getInsure_object(), contract.getType_contract(), contract.getDerivative_id()));
    }

    private static String[] columnsInsure = {"contract_id", "name_org", "address_org", "surname_client", "first_name_client",
            "mid_name_client", "address_client", "passport_id", "date_start", "validity",
            "currency", "insure_event", "insure_obj", "payment_amount",
            "contributions_client", "risk_lvl", "type_contract", "derivative_id"};

    public static String[] getColumnsInsure() {
        return columnsInsure;
    }
}
