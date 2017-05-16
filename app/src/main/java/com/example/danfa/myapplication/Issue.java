package com.example.danfa.myapplication;


public class Issue {//implements Parcelable{

    private String name;
    private String description;


    private String imageUrl;
    int issues_count;

//    protected Issue(Parcel in) {
//        name = in.readString();
//        description = in.readString();
//        issues_count = in.readInt();
//    }



    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() {  return description; }

    public void setDescription(String description) { this.description = description; }

    public int getIssues_count() { return issues_count; }

    public void setIssues_count(int issues_count) { this.issues_count = issues_count; }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Issue(String name, String description, int issues_count, String imageUrl) {
        this.name = name;
        this.description = description;
        this.issues_count = issues_count;
        this.imageUrl = imageUrl;

    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(name);
//        dest.writeString(description);
//        dest.writeInt(issues_count);
//    }
//
//    public static final Creator<Issue> CREATOR = new Creator<Issue>() {
//        @Override
//        public Issue createFromParcel(Parcel in) {
//            return new Issue(in);
//        }
//
//        @Override
//        public Issue[] newArray(int size) {
//            return new Issue[size];
//        }
//    };
}
