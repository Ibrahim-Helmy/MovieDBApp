
package com.example.ibrahim_pc.rv.Trailerpakg;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrailerResponse implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<Trailer> results = null;
    public final static Creator<TrailerResponse> CREATOR = new Creator<TrailerResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public TrailerResponse createFromParcel(Parcel in) {
            return new TrailerResponse(in);
        }

        public TrailerResponse[] newArray(int size) {
            return (new TrailerResponse[size]);
        }

    }
    ;

    protected TrailerResponse(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.results, (com.example.ibrahim_pc.rv.Trailerpakg.Trailer.class.getClassLoader()));
    }

    public TrailerResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Trailer> getResults() {
        return results;
    }

    public void setResults(List<Trailer> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeList(results);
    }

    public int describeContents() {
        return  0;
    }

}
