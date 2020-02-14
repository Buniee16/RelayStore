package com.example.relaystore.test_model;

public class SearchOrder {


    private String NumId;
    private String NIC_Handle;
    private String D_Creation;
    private String Reference;
    private String Nombre;
    private String Poids;
    private String Zone;
    private String Lieu;
    private String Livraison;
    private String Paiement;
    private String MSISDN;
    private String VISA_Nom;
    private String VISA_Code;
    private String Code_Coupon;
    private String Devise;
    private String Taux;
    private String Commande;
    private String Coupon;
    private String Message;
    private String Adresse;
    private String Montant;
    private String Transport;
    private String Net_A_Payer;
    private String Facture_FCF;
    private String Bordereau_Paiement;
    private String Montant_Recu;
    private String Bordereau_Livraison;
    private String Montant_Livraison;
    private String Etat_Livraison;
    private String Facture_Livraison;
    private String D_Attente;
    private String D_Preparation;
    private String D_Expedition;
    private String D_Termine;
    private String D_Annuler;
    private String Etat_Commande;


    public SearchOrder(String numId, String NIC_Handle, String d_Creation, String reference, String nombre, String poids, String zone, String lieu, String livraison, String paiement, String MSISDN, String VISA_Nom, String VISA_Code, String code_Coupon, String devise, String taux, String commande, String coupon, String message, String adresse, String montant, String transport, String net_A_Payer, String facture_FCF, String bordereau_Paiement, String montant_Recu, String bordereau_Livraison, String montant_Livraison, String etat_Livraison, String facture_Livraison, String d_Attente, String d_Preparation, String d_Expedition, String d_Termine, String d_Annuler, String etat_Commande) {
        NumId = numId;
        this.NIC_Handle = NIC_Handle;
        D_Creation = d_Creation;
        Reference = reference;
        Nombre = nombre;
        Poids = poids;
        Zone = zone;
        Lieu = lieu;
        Livraison = livraison;
        Paiement = paiement;
        this.MSISDN = MSISDN;
        this.VISA_Nom = VISA_Nom;
        this.VISA_Code = VISA_Code;
        Code_Coupon = code_Coupon;
        Devise = devise;
        Taux = taux;
        Commande = commande;
        Coupon = coupon;
        Message = message;
        Adresse = adresse;
        Montant = montant;
        Transport = transport;
        Net_A_Payer = net_A_Payer;
        Facture_FCF = facture_FCF;
        Bordereau_Paiement = bordereau_Paiement;
        Montant_Recu = montant_Recu;
        Bordereau_Livraison = bordereau_Livraison;
        Montant_Livraison = montant_Livraison;
        Etat_Livraison = etat_Livraison;
        Facture_Livraison = facture_Livraison;
        D_Attente = d_Attente;
        D_Preparation = d_Preparation;
        D_Expedition = d_Expedition;
        D_Termine = d_Termine;
        D_Annuler = d_Annuler;
        Etat_Commande = etat_Commande;
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

    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPoids() {
        return Poids;
    }

    public void setPoids(String poids) {
        Poids = poids;
    }

    public String getZone() {
        return Zone;
    }

    public void setZone(String zone) {
        Zone = zone;
    }

    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String lieu) {
        Lieu = lieu;
    }

    public String getLivraison() {
        return Livraison;
    }

    public void setLivraison(String livraison) {
        Livraison = livraison;
    }

    public String getPaiement() {
        return Paiement;
    }

    public void setPaiement(String paiement) {
        Paiement = paiement;
    }

    public String getMSISDN() {
        return MSISDN;
    }

    public void setMSISDN(String MSISDN) {
        this.MSISDN = MSISDN;
    }

    public String getVISA_Nom() {
        return VISA_Nom;
    }

    public void setVISA_Nom(String VISA_Nom) {
        this.VISA_Nom = VISA_Nom;
    }

    public String getVISA_Code() {
        return VISA_Code;
    }

    public void setVISA_Code(String VISA_Code) {
        this.VISA_Code = VISA_Code;
    }

    public String getCode_Coupon() {
        return Code_Coupon;
    }

    public void setCode_Coupon(String code_Coupon) {
        Code_Coupon = code_Coupon;
    }

    public String getDevise() {
        return Devise;
    }

    public void setDevise(String devise) {
        Devise = devise;
    }

    public String getTaux() {
        return Taux;
    }

    public void setTaux(String taux) {
        Taux = taux;
    }

    public String getCommande() {
        return Commande;
    }

    public void setCommande(String commande) {
        Commande = commande;
    }

    public String getCoupon() {
        return Coupon;
    }

    public void setCoupon(String coupon) {
        Coupon = coupon;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getMontant() {
        return Montant;
    }

    public void setMontant(String montant) {
        Montant = montant;
    }

    public String getTransport() {
        return Transport;
    }

    public void setTransport(String transport) {
        Transport = transport;
    }

    public String getNet_A_Payer() {
        return Net_A_Payer;
    }

    public void setNet_A_Payer(String net_A_Payer) {
        Net_A_Payer = net_A_Payer;
    }

    public String getFacture_FCF() {
        return Facture_FCF;
    }

    public void setFacture_FCF(String facture_FCF) {
        Facture_FCF = facture_FCF;
    }

    public String getBordereau_Paiement() {
        return Bordereau_Paiement;
    }

    public void setBordereau_Paiement(String bordereau_Paiement) {
        Bordereau_Paiement = bordereau_Paiement;
    }

    public String getMontant_Recu() {
        return Montant_Recu;
    }

    public void setMontant_Recu(String montant_Recu) {
        Montant_Recu = montant_Recu;
    }

    public String getBordereau_Livraison() {
        return Bordereau_Livraison;
    }

    public void setBordereau_Livraison(String bordereau_Livraison) {
        Bordereau_Livraison = bordereau_Livraison;
    }

    public String getMontant_Livraison() {
        return Montant_Livraison;
    }

    public void setMontant_Livraison(String montant_Livraison) {
        Montant_Livraison = montant_Livraison;
    }

    public String getEtat_Livraison() {
        return Etat_Livraison;
    }

    public void setEtat_Livraison(String etat_Livraison) {
        Etat_Livraison = etat_Livraison;
    }

    public String getFacture_Livraison() {
        return Facture_Livraison;
    }

    public void setFacture_Livraison(String facture_Livraison) {
        Facture_Livraison = facture_Livraison;
    }

    public String getD_Attente() {
        return D_Attente;
    }

    public void setD_Attente(String d_Attente) {
        D_Attente = d_Attente;
    }

    public String getD_Preparation() {
        return D_Preparation;
    }

    public void setD_Preparation(String d_Preparation) {
        D_Preparation = d_Preparation;
    }

    public String getD_Expedition() {
        return D_Expedition;
    }

    public void setD_Expedition(String d_Expedition) {
        D_Expedition = d_Expedition;
    }

    public String getD_Termine() {
        return D_Termine;
    }

    public void setD_Termine(String d_Termine) {
        D_Termine = d_Termine;
    }

    public String getD_Annuler() {
        return D_Annuler;
    }

    public void setD_Annuler(String d_Annuler) {
        D_Annuler = d_Annuler;
    }

    public String getEtat_Commande() {
        return Etat_Commande;
    }

    public void setEtat_Commande(String etat_Commande) {
        Etat_Commande = etat_Commande;
    }
}
