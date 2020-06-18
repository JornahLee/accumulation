package com.jornah.pojo;

public class UserDTO {
    public String username;
    public String nickname;
    public String email;

    // extra

    public String token;

    public String active_dt;
    public int active_only;
    public String cease_date;
    public int comp_inst_id;
    public byte comp_inst_id_serv;
    public int component_id;
    public int component_instance_id;
    public byte component_instance_id_serv;
    public short connect_reason;
    public short disconnect_reason;
    public String external_id;
    public short external_id_type;
    public String inactive_dt;
    public String owning_external_id;
    public short owning_external_id_type;
    public int package_id;
    public int package_instance_id;
    public byte package_instance_id_serv;
    public char package_status;
    public String prev_end_dt;
    public byte vtServerID;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getActive_dt() {
        return active_dt;
    }

    public void setActive_dt(String active_dt) {
        this.active_dt = active_dt;
    }

    public int getActive_only() {
        return active_only;
    }

    public void setActive_only(int active_only) {
        this.active_only = active_only;
    }

    public String getCease_date() {
        return cease_date;
    }

    public void setCease_date(String cease_date) {
        this.cease_date = cease_date;
    }

    public int getComp_inst_id() {
        return comp_inst_id;
    }

    public void setComp_inst_id(int comp_inst_id) {
        this.comp_inst_id = comp_inst_id;
    }

    public byte getComp_inst_id_serv() {
        return comp_inst_id_serv;
    }

    public void setComp_inst_id_serv(byte comp_inst_id_serv) {
        this.comp_inst_id_serv = comp_inst_id_serv;
    }

    public int getComponent_id() {
        return component_id;
    }

    public void setComponent_id(int component_id) {
        this.component_id = component_id;
    }

    public int getComponent_instance_id() {
        return component_instance_id;
    }

    public void setComponent_instance_id(int component_instance_id) {
        this.component_instance_id = component_instance_id;
    }

    public byte getComponent_instance_id_serv() {
        return component_instance_id_serv;
    }

    public void setComponent_instance_id_serv(byte component_instance_id_serv) {
        this.component_instance_id_serv = component_instance_id_serv;
    }

    public short getConnect_reason() {
        return connect_reason;
    }

    public void setConnect_reason(short connect_reason) {
        this.connect_reason = connect_reason;
    }

    public short getDisconnect_reason() {
        return disconnect_reason;
    }

    public void setDisconnect_reason(short disconnect_reason) {
        this.disconnect_reason = disconnect_reason;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public short getExternal_id_type() {
        return external_id_type;
    }

    public void setExternal_id_type(short external_id_type) {
        this.external_id_type = external_id_type;
    }

    public String getInactive_dt() {
        return inactive_dt;
    }

    public void setInactive_dt(String inactive_dt) {
        this.inactive_dt = inactive_dt;
    }

    public String getOwning_external_id() {
        return owning_external_id;
    }

    public void setOwning_external_id(String owning_external_id) {
        this.owning_external_id = owning_external_id;
    }

    public short getOwning_external_id_type() {
        return owning_external_id_type;
    }

    public void setOwning_external_id_type(short owning_external_id_type) {
        this.owning_external_id_type = owning_external_id_type;
    }

    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }

    public int getPackage_instance_id() {
        return package_instance_id;
    }

    public void setPackage_instance_id(int package_instance_id) {
        this.package_instance_id = package_instance_id;
    }

    public byte getPackage_instance_id_serv() {
        return package_instance_id_serv;
    }

    public void setPackage_instance_id_serv(byte package_instance_id_serv) {
        this.package_instance_id_serv = package_instance_id_serv;
    }

    public char getPackage_status() {
        return package_status;
    }

    public void setPackage_status(char package_status) {
        this.package_status = package_status;
    }

    public String getPrev_end_dt() {
        return prev_end_dt;
    }

    public void setPrev_end_dt(String prev_end_dt) {
        this.prev_end_dt = prev_end_dt;
    }

    public byte getVtServerID() {
        return vtServerID;
    }

    public void setVtServerID(byte vtServerID) {
        this.vtServerID = vtServerID;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", active_dt='" + active_dt + '\'' +
                ", active_only=" + active_only +
                ", cease_date='" + cease_date + '\'' +
                ", comp_inst_id=" + comp_inst_id +
                ", comp_inst_id_serv=" + comp_inst_id_serv +
                ", component_id=" + component_id +
                ", component_instance_id=" + component_instance_id +
                ", component_instance_id_serv=" + component_instance_id_serv +
                ", connect_reason=" + connect_reason +
                ", disconnect_reason=" + disconnect_reason +
                ", external_id='" + external_id + '\'' +
                ", external_id_type=" + external_id_type +
                ", inactive_dt='" + inactive_dt + '\'' +
                ", owning_external_id='" + owning_external_id + '\'' +
                ", owning_external_id_type=" + owning_external_id_type +
                ", package_id=" + package_id +
                ", package_instance_id=" + package_instance_id +
                ", package_instance_id_serv=" + package_instance_id_serv +
                ", package_status=" + package_status +
                ", prev_end_dt='" + prev_end_dt + '\'' +
                ", vtServerID=" + vtServerID +
                '}';
    }
}
