package com.lpdm.msstore.model.location;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Kybox
 * @version 1.0
 * @since 01/12/2018
 */

public class City {

    private int id;
    @JsonIgnore
    private String departmentCode;
    private String inseeCode;
    private String zipCode;
    private String name;
    private String slug;
    private double latitude;
    private double longitude;
    private Department department;

    public City() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getInseeCode() {
        return inseeCode;
    }

    public void setInseeCode(String inseeCode) {
        this.inseeCode = inseeCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", departmentCode='" + departmentCode + '\'' +
                ", inseeCode='" + inseeCode + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", department=" + department +
                '}';
    }
}
