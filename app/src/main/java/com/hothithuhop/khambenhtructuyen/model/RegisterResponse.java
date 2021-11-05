package com.hothithuhop.khambenhtructuyen.model;

public class RegisterResponse {
     int Status;
     String Notification;
     String Taikhoan;
     String Matkhau;

     public RegisterResponse(int status, String notification, String taikhoan, String matkhau) {
          Status = status;
          Notification = notification;
          Taikhoan = taikhoan;
          Matkhau = matkhau;
     }

     public int getStatus() {
          return Status;
     }

     public void setStatus(int status) {
          Status = status;
     }

     public String getNotification() {
          return Notification;
     }

     public void setNotification(String notification) {
          Notification = notification;
     }

     public String getTaikhoan() {
          return Taikhoan;
     }

     public void setTaikhoan(String taikhoan) {
          Taikhoan = taikhoan;
     }

     public String getMatkhau() {
          return Matkhau;
     }

     public void setMatkhau(String matkhau) {
          Matkhau = matkhau;
     }
}
