package com.example.relaystore.test_model;

import com.google.gson.annotations.SerializedName;

public class UserOrderDetails {

    @SerializedName("NumId")
    private String NumId;
    private String NIC_Handle;
    private String D_Creation;
    private String Net_A_Payer;
    private String Paiement;
    private String Adresse;
    private String Montant_Livraison;

    public UserOrderDetails(String numId, String NIC_Handle, String d_Creation, String net_A_Payer, String paiement, String adresse, String montant_Livraison) {
        NumId = numId;
        this.NIC_Handle = NIC_Handle;
        D_Creation = d_Creation;
        Net_A_Payer = net_A_Payer;
        Paiement = paiement;
        Adresse = adresse;
        Montant_Livraison = montant_Livraison;
    }

    public String getNumId() {
        return NumId;
    }

    public void setNumId(String numId) {
        NumId = numId;
    }

    public String getNIC_Handle() {
        return NIC_Handle;
    }

    public void setNIC_Handle(String NIC_Handle) {
        this.NIC_Handle = NIC_Handle;
    }

    public String getD_Creation() {
        return D_Creation;
    }

    public void setD_Creation(String d_Creation) {
        D_Creation = d_Creation;
    }

    public String getNet_A_Payer() {
        return Net_A_Payer;
    }

    public void setNet_A_Payer(String net_A_Payer) {
        Net_A_Payer = net_A_Payer;
    }

    public String getPaiement() {
        return Paiement;
    }

    public void setPaiement(String paiement) {
        Paiement = paiement;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getMontant_Livraison() {
        return Montant_Livraison;
    }

    public void setMontant_Livraison(String montant_Livraison) {
        Montant_Livraison = montant_Livraison;
    }
}
