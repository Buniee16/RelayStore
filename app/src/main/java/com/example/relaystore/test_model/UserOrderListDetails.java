package com.example.relaystore.test_model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserOrderListDetails {


    @SerializedName("status")
    private String status;

    @SerializedName("msg")
    private String msg;


    @SerializedName("data")
    private List<ProductAvlShop> datalist = null;


    public UserOrderListDetails(String status, String msg, List<ProductAvlShop> data) {
        this.status = status;
        this.msg = msg;
        this.datalist = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ProductAvlShop> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<ProductAvlShop> datalist) {
        this.datalist = datalist;
    }

    public class ProductAvlShop {

        private String NumId;
        private String NIC_Handle;
        private String D_Creation;
        private String Reference;
        private String Libelle_TAG;
        private String Quantite;
        private String Prix_Unitaire;
        private String Prix_Total;
        private String Remise;
        private String Total;
        private String product_image;

        public ProductAvlShop(String numId, String NIC_Handle, String d_Creation, String reference, String libelle_TAG, String quantite, String prix_Unitaire, String prix_Total, String remise, String total,String product_image) {
            NumId = numId;
            this.NIC_Handle = NIC_Handle;
            D_Creation = d_Creation;
            Reference = reference;
            Libelle_TAG = libelle_TAG;
            Quantite = quantite;
            Prix_Unitaire = prix_Unitaire;
            Prix_Total = prix_Total;
            Remise = remise;
            Total = total;
            product_image = product_image;
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

        public String getLibelle_TAG() {
            return Libelle_TAG;
        }

        public void setLibelle_TAG(String libelle_TAG) {
            Libelle_TAG = libelle_TAG;
        }

        public String getQuantite() {
            return Quantite;
        }

        public void setQuantite(String quantite) {
            Quantite = quantite;
        }

        public String getPrix_Unitaire() {
            return Prix_Unitaire;
        }

        public void setPrix_Unitaire(String prix_Unitaire) {
            Prix_Unitaire = prix_Unitaire;
        }

        public String getPrix_Total() {
            return Prix_Total;
        }

        public void setPrix_Total(String prix_Total) {
            Prix_Total = prix_Total;
        }

        public String getRemise() {
            return Remise;
        }

        public void setRemise(String remise) {
            Remise = remise;
        }

        public String getTotal() {
            return Total;
        }

        public void setTotal(String total) {
            Total = total;
        }

        public String getProduct_image() {
            return product_image;
        }

        public void setProduct_image(String product_image) {
            this.product_image = product_image;
        }
    }






}
