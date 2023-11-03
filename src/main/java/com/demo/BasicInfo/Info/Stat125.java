package com.demo.BasicInfo.Info;

import jakarta.persistence.Entity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;


@Entity
@Table(name = "stat125")
public class Stat125 extends Stats{
}
