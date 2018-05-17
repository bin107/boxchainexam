package com.example.dobin.boxchainexam.component.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Elements implements Parcelable {

    public String id;
    public String type;
    public String data;
    public List<Fields> fields;

    public Elements() {

    }

    protected Elements(Parcel in) {
        id = in.readString();
        type = in.readString();
        data = in.readString();
        fields = in.readArrayList(Fields.class.getClassLoader());
    }

    public static final Creator<Elements> CREATOR = new Creator<Elements>() {
        @Override
        public Elements createFromParcel(Parcel in) {
            return new Elements(in);
        }

        @Override
        public Elements[] newArray(int size) {
            return new Elements[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(type);
        parcel.writeString(data);
        parcel.writeTypedList(fields);

    }

    public static class Fields implements Parcelable {
        public String id;
        public String label;
        public String type;


        public Fields() {
        }


        protected Fields(Parcel in) {
            id = in.readString();
            label = in.readString();
            type = in.readString();
        }

        public static final Creator<Fields> CREATOR = new Creator<Fields>() {
            @Override
            public Fields createFromParcel(Parcel in) {
                return new Fields(in);
            }

            @Override
            public Fields[] newArray(int size) {
                return new Fields[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(id);
            parcel.writeString(label);
            parcel.writeString(type);
        }
    }

    public static Elements parseElementsFromResponse(JsonObject response) {

        JsonArray jaElements = response.getAsJsonArray("elements");
        List<Elements> elementsList = new ArrayList<>();
        for (JsonElement jElements : jaElements) {
            JsonObject obj = jElements.getAsJsonObject();
            Elements elements = new Elements();
            elements.id = obj.get("id").getAsString();
            elements.type = obj.get("type").getAsString();
            elements.data = obj.get("data").getAsString();

            JsonArray jaFields = obj.getAsJsonArray("fields");
            List<Fields> fieldsList = new ArrayList<>();

            for (JsonElement jeFields : jaFields) {
                JsonObject objFields = jeFields.getAsJsonObject();
                Fields fields = new Fields();
                fields.id = objFields.get("id").getAsString();
                fields.label = objFields.get("label").getAsString();
                fields.type = objFields.get("type").getAsString();

                fieldsList.add(fields);
                elements.fields = fieldsList;
            }
            elementsList.add(elements);

        }


        return elementsList.get(0);
    }

    /**
     * test
     * @return
     */
    public static Elements parseElementsFromResponse2() {
        String txt = "{\n" +
                "\t\"activityTitle\": \"Boxchain\",\n" +
                "\t\"elements\": [{\n" +
                "\t\t\"id\": \"userInfoList\",\n" +
                "\t\t\"type\": \"list\",\n" +
                "\t\t\"data\": \"http://www.mocky.io/v2/5afaa6902e00005d00278ffb\",\n" +
                "\t\t\"fields\": [{\n" +
                "\t\t\t\t\"id\": \"age\",\n" +
                "\t\t\t\t\"label\": \"Age\",\n" +
                "\t\t\t\t\"type\": \"textView\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"id\": \"fullName\",\n" +
                "\t\t\t\t\"label\": \"Full Name\",\n" +
                "\t\t\t\t\"type\": \"textView\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"id\": \"email\",\n" +
                "\t\t\t\t\"label\": \"Email\",\n" +
                "\t\t\t\t\"type\": \"textView\"\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t}]\n" +
                "}";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jelem = gson.fromJson(txt, JsonElement.class);
        JsonObject response = jelem.getAsJsonObject();


        JsonArray jaElements = response.getAsJsonArray("elements");
        List<Elements> elementsList = new ArrayList<>();
        for (JsonElement jElements : jaElements) {
            JsonObject obj = jElements.getAsJsonObject();
            Elements elements = new Elements();
            elements.id = obj.get("id").getAsString();
            elements.type = obj.get("type").getAsString();
            elements.data = obj.get("data").getAsString();

            JsonArray jaFields = obj.getAsJsonArray("fields");
            List<Fields> fieldsList = new ArrayList<>();

            for (JsonElement jeFields : jaFields) {
                JsonObject objFields = jeFields.getAsJsonObject();
                Fields fields = new Fields();
                fields.id = objFields.get("id").getAsString();
                fields.label = objFields.get("label").getAsString();
                fields.type = objFields.get("type").getAsString();

                fieldsList.add(fields);
                elements.fields = fieldsList;
            }
            elementsList.add(elements);

        }


        return elementsList.get(0);
    }
}
