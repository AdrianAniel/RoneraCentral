package com.adriananiel.roneracentral.Corzo;

import java.io.Serializable;

public class QualityRonTable implements Serializable {
    String product;
    String quality;
    String amount;
    String avb;
    String turbidity;
    String color;
    String chemical_analysis ;
    String microbiological_analysis ;
    String flavor;
    String aroma ;
    String pack_and_label ;
    String manufacturing_process;
    String equipement;
    String facilities ;
    String human_factor;
    String filtration;
    String aging ;
    String blend;
    String level_ph ;
    String rawn_material ;

    public QualityRonTable(String product, String quality, String amount, String avb, String turbidity, String color, String chemical_analysis, String microbiological_analysis, String flavor, String aroma, String pack_and_label, String manufacturing_process, String equipement, String facilities, String human_factor, String filtration, String aging, String blend, String level_ph, String rawn_material) {
        this.product = product;
        this.quality = quality;
        this.amount = amount;
        this.avb = avb;
        this.turbidity = turbidity;
        this.color = color;
        this.chemical_analysis = chemical_analysis;
        this.microbiological_analysis = microbiological_analysis;
        this.flavor = flavor;
        this.aroma = aroma;
        this.pack_and_label = pack_and_label;
        this.manufacturing_process = manufacturing_process;
        this.equipement = equipement;
        this.facilities = facilities;
        this.human_factor = human_factor;
        this.filtration = filtration;
        this.aging = aging;
        this.blend = blend;
        this.level_ph = level_ph;
        this.rawn_material = rawn_material;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getAvb() {
        return avb;
    }

    public void setAvb(String avb) {
        this.avb = avb;
    }

    public String getTurbidity() {
        return turbidity;
    }

    public void setTurbidity(String turbidity) {
        this.turbidity = turbidity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getChemical_analysis() {
        return chemical_analysis;
    }

    public void setChemical_analysis(String chemical_analysis) {
        this.chemical_analysis = chemical_analysis;
    }

    public String getMicrobiological_analysis() {
        return microbiological_analysis;
    }

    public void setMicrobiological_analysis(String microbiological_analysis) {
        this.microbiological_analysis = microbiological_analysis;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getAroma() {
        return aroma;
    }

    public void setAroma(String aroma) {
        this.aroma = aroma;
    }

    public String getPack_and_label() {
        return pack_and_label;
    }

    public void setPack_and_label(String pack_and_label) {
        this.pack_and_label = pack_and_label;
    }

    public String getManufacturing_process() {
        return manufacturing_process;
    }

    public void setManufacturing_process(String manufacturing_process) {
        this.manufacturing_process = manufacturing_process;
    }

    public String getEquipement() {
        return equipement;
    }

    public void setEquipement(String equipement) {
        this.equipement = equipement;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getHuman_factor() {
        return human_factor;
    }

    public void setHuman_factor(String human_factor) {
        this.human_factor = human_factor;
    }

    public String getFiltration() {
        return filtration;
    }

    public void setFiltration(String filtration) {
        this.filtration = filtration;
    }

    public String getAging() {
        return aging;
    }

    public void setAging(String aging) {
        this.aging = aging;
    }

    public String getBlend() {
        return blend;
    }

    public void setBlend(String blend) {
        this.blend = blend;
    }

    public String getLevel_ph() {
        return level_ph;
    }

    public void setLevel_ph(String level_ph) {
        this.level_ph = level_ph;
    }

    public String getRawn_material() {
        return rawn_material;
    }

    public void setRawn_material(String rawn_material) {
        this.rawn_material = rawn_material;
    }
}
