package com.example.swuclubmanage.com.wsf.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 王书发 on 2016/6/15.
 */
public class ClubInfo implements Parcelable {
    private String clubName;
    private String clubMaster;
    private String imgUrl;
    public int clubStar;
    public String joinClubTime;



    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }





    public String getClubMaster() {
        return clubMaster;
    }

    public void setClubMaster(String clubMaster) {
        this.clubMaster = clubMaster;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public int getClubStar() {
        return clubStar;
    }

    public void setClubStar(int clubStar) {
        this.clubStar = clubStar;
    }

    public String getJoinClubTime() {
        return joinClubTime;
    }

    public void setJoinClubTime(String joinClubTime) {
        this.joinClubTime = joinClubTime;
    }








    public static final Parcelable.Creator<ClubInfo> CREATOR = new Creator<ClubInfo>() {
        public ClubInfo createFromParcel(Parcel source) {
            ClubInfo clubInfo = new ClubInfo();
            clubInfo.clubName = source.readString();
            clubInfo.clubMaster=source.readString();
            clubInfo.joinClubTime=source.readString();
            clubInfo.clubStar = source.readInt();
            clubInfo.imgUrl=source.readString();
            return clubInfo;
        }

        public ClubInfo[] newArray(int size) {
            return new ClubInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 将实体类数据写入Parcel
     */
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(clubName);
        parcel.writeString(clubMaster);
        parcel.writeString(joinClubTime);
        parcel.writeInt(clubStar);
        parcel.writeString(imgUrl);
    }
}
