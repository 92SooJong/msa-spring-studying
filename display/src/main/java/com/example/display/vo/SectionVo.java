package com.example.display.vo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME, property = "sectionType", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = SectionBannerVo.class, name = "BANNER"),
    @JsonSubTypes.Type(value = SectionQuickButtonVo.class, name = "QUICK_BUTTON")

})
public abstract class SectionVo {

    private String sectionType;

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }
}
