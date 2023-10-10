package com.geipl.task2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Model {

    @SerializedName("Status")
    private Boolean Status;
    @SerializedName("Message")
    private String Message;
    @SerializedName("Errortype")
    private int Errortype;

    @SerializedName("listTraceabilityML")
    private ArrayList<listTraceabilityML> data;
    public Boolean getStatus() {
        return Status;
    }

    public String getMessage() {
        return Message!=null ? Message:"";
    }

    public int getErrortype() {
        return Errortype;
    }

    public ArrayList<listTraceabilityML> getData() {
        return data!=null? data:new ArrayList<>();
    }

    public static class listTraceabilityML implements Parcelable{

        @SerializedName("ID")
        private int id;
        @SerializedName("PlantCode")
        private String plantCode;
        @SerializedName("WarehouseCode")
        private String warehouseCode;
        @SerializedName("MachineCode")
        private String machineCode;
        @SerializedName("UserCode")
        private String userCode;
        @SerializedName("PalletCode")
        private String palletCode;
        @SerializedName("Quantity")
        private double quantity;
        @SerializedName("Status")
        private boolean Status;
        @SerializedName("AddedBy")
        private boolean AddedBy;
        @SerializedName("Added")
        private String Added;
        @SerializedName("LastModifiedBy")
        private boolean LastModifiedBy;
        @SerializedName("LastModifiedOn")
        private String LastModifiedOn;
        @SerializedName("Remarks")
        private boolean Remarks;
        @SerializedName("NewPalletCode")
        private boolean NewPalletCode;
        @SerializedName("RejectQuantity")
        private double RejectQuantity;
        @SerializedName("Trolley")
        private boolean Trolley;
        @SerializedName("lstdata")
        private boolean lstdata;
        @SerializedName("listQuantityAndRemark")
        private boolean listQuantityAndRemark;

        protected listTraceabilityML(Parcel in) {
            id = in.readInt();
            plantCode = in.readString();
            warehouseCode = in.readString();
            machineCode = in.readString();
            userCode = in.readString();
            palletCode = in.readString();
            quantity = in.readDouble();
            Status = in.readByte() != 0;
            AddedBy = in.readByte() != 0;
            Added = in.readString();
            LastModifiedBy = in.readByte() != 0;
            LastModifiedOn = in.readString();
            Remarks = in.readByte() != 0;
            NewPalletCode = in.readByte() != 0;
            RejectQuantity = in.readDouble();
            Trolley = in.readByte() != 0;
            lstdata = in.readByte() != 0;
            listQuantityAndRemark = in.readByte() != 0;
        }

        public static final Creator<listTraceabilityML> CREATOR = new Creator<listTraceabilityML>() {
            @Override
            public listTraceabilityML createFromParcel(Parcel in) {
                return new listTraceabilityML(in);
            }

            @Override
            public listTraceabilityML[] newArray(int size) {
                return new listTraceabilityML[size];
            }
        };

        public boolean isStatus() {
            return Status;
        }

        public boolean isAddedBy() {
            return AddedBy;
        }

        public String getAdded() {
            return Added;
        }

        public boolean isLastModifiedBy() {
            return LastModifiedBy;
        }

        public String getLastModifiedOn() {
            return LastModifiedOn;
        }

        public boolean isRemarks() {
            return Remarks;
        }

        public boolean isNewPalletCode() {
            return NewPalletCode;
        }

        public double getRejectQuantity() {
            return RejectQuantity;
        }

        public boolean isTrolley() {
            return Trolley;
        }

        public boolean isLstdata() {
            return lstdata;
        }

        public boolean isListQuantityAndRemark() {
            return listQuantityAndRemark;
        }

        public int getId() {
            return id;
        }

        public String getPlantCode() {
            return plantCode;
        }

        public String getWarehouseCode() {
            return warehouseCode;
        }

        public String getMachineCode() {
            return machineCode;
        }

        public String getUserCode() {
            return userCode;
        }

        public String getPalletCode() {
            return palletCode;
        }

        public double getQuantity() {
            return quantity;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(plantCode);
            dest.writeString(warehouseCode);
            dest.writeString(machineCode);
            dest.writeString(userCode);
            dest.writeString(palletCode);
            dest.writeDouble(quantity);
            dest.writeByte((byte) (Status ? 1 : 0));
            dest.writeByte((byte) (AddedBy ? 1 : 0));
            dest.writeString(Added);
            dest.writeByte((byte) (LastModifiedBy ? 1 : 0));
            dest.writeString(LastModifiedOn);
            dest.writeByte((byte) (Remarks ? 1 : 0));
            dest.writeByte((byte) (NewPalletCode ? 1 : 0));
            dest.writeDouble(RejectQuantity);
            dest.writeByte((byte) (Trolley ? 1 : 0));
            dest.writeByte((byte) (lstdata ? 1 : 0));
            dest.writeByte((byte) (listQuantityAndRemark ? 1 : 0));
        }
    }

}

