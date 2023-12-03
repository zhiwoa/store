package com.demos.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class District extends BaseEntity{
   public Integer id ;
   public String parent;
   public String  code ;
   public String  name;
}
