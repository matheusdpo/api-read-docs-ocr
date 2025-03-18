package com.lumen.api.v1.models.documents;

public class DocumentPASSPORTBrazil {

    private String name;
    private String surname;
    private String type;
    private String issuingCountry;
    private String passportNumber;
    private String nationality;
    private String birthDate;
    private String birthPlace;
    private String sex;
    private String motherName;
    private String fatherName;
    private String dateIssue;
    private String dateExpiry;
    private String authority;
    private String mrzCode;
    private String observations;

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", type='" + type + '\'' +
                ", issuingCountry='" + issuingCountry + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", nationality='" + nationality + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", sex='" + sex + '\'' +
                ", motherName='" + motherName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", dateIssue='" + dateIssue + '\'' +
                ", dateExpiry='" + dateExpiry + '\'' +
                ", authority='" + authority + '\'' +
                ", mrzCode='" + mrzCode + '\'' +
                ", observations='" + observations + '\'' +
                '}';
    }

    //getter
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getType() {
        return type;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public String getSex() {
        return sex;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getDateIssue() {
        return dateIssue;
    }

    public String getDateExpiry() {
        return dateExpiry;
    }

    public String getAuthority() {
        return authority;
    }

    public String getMrzCode() {
        return mrzCode;
    }

    public String getObservations() {
        return observations;
    }

    //setter
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public void setIssuingCountry(String issuingCountry) {
        this.issuingCountry = issuingCountry;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setDateIssue(String dateIssue) {
        this.dateIssue = dateIssue;
    }

    public void setDateExpiry(String dateExpiry) {
        this.dateExpiry = dateExpiry;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setMrzCode(String mrzCode) {
        this.mrzCode = mrzCode;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}