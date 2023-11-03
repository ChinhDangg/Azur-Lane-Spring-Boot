package com.demo.BasicInfo.Info;

import jakarta.persistence.Entity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

@Entity
@Table(name = "stat120")
public class Stat120 extends Stats {


}
