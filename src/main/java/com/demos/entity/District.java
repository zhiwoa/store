package com.demos.entity;




public class District extends BaseEntity{
   public Integer id ;
   public String parent;
   public String  code ;
   public String  name;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getParent() {
      return parent;
   }

   public void setParent(String parent) {
      this.parent = parent;
   }

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public String toString() {
      return "District{" +
              "id=" + id +
              ", parent='" + parent + '\'' +
              ", code='" + code + '\'' +
              ", name='" + name + '\'' +
              '}';
   }
}
