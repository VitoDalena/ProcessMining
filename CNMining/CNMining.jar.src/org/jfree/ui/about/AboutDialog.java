/*     */ package org.jfree.ui.about;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Image;
/*     */ import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.border.Border;
/*     */ import org.jfree.util.ResourceBundleWrapper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AboutDialog
/*     */   extends JDialog
/*     */ {
/*  72 */   public static final Dimension PREFERRED_SIZE = new Dimension(560, 360);
/*     */   
/*     */ 
/*  75 */   public static final Border STANDARD_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);
/*     */   
/*     */ 
/*     */ 
/*     */   private ResourceBundle resources;
/*     */   
/*     */ 
/*     */ 
/*     */   private String application;
/*     */   
/*     */ 
/*     */ 
/*     */   private String version;
/*     */   
/*     */ 
/*     */ 
/*     */   private String copyright;
/*     */   
/*     */ 
/*     */ 
/*     */   private String info;
/*     */   
/*     */ 
/*     */   private Image logo;
/*     */   
/*     */ 
/*     */   private List contributors;
/*     */   
/*     */ 
/*     */   private String licence;
/*     */   
/*     */ 
/*     */ 
/*     */   public AboutDialog(String title, ProjectInfo project)
/*     */   {
/* 110 */     init(title, project.getName(), "Version " + project.getVersion(), project.getInfo(), project.getLogo(), project.getCopyright(), project.getLicenceText(), project.getContributors(), project);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AboutDialog(Frame owner, String title, ProjectInfo project)
/*     */   {
/* 134 */     super(owner);
/* 135 */     init(title, project.getName(), "Version " + project.getVersion(), project.getInfo(), project.getLogo(), project.getCopyright(), project.getLicenceText(), project.getContributors(), project);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AboutDialog(Dialog owner, String title, ProjectInfo project)
/*     */   {
/* 158 */     super(owner);
/* 159 */     init(title, project.getName(), "Version " + project.getVersion(), project.getInfo(), project.getLogo(), project.getCopyright(), project.getLicenceText(), project.getContributors(), project);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void init(String title, String application, String version, String info, Image logo, String copyright, String licence, List contributors, ProjectInfo libraries)
/*     */   {
/* 193 */     setTitle(title);
/*     */     
/* 195 */     this.application = application;
/* 196 */     this.version = version;
/* 197 */     this.copyright = copyright;
/* 198 */     this.info = info;
/* 199 */     this.logo = logo;
/* 200 */     this.contributors = contributors;
/* 201 */     this.licence = licence;
/*     */     
/* 203 */     String baseName = "org.jfree.ui.about.resources.AboutResources";
/* 204 */     this.resources = ResourceBundleWrapper.getBundle("org.jfree.ui.about.resources.AboutResources");
/*     */     
/* 206 */     JPanel content = new JPanel(new BorderLayout());
/* 207 */     content.setBorder(STANDARD_BORDER);
/*     */     
/* 209 */     JTabbedPane tabs = createTabs(libraries);
/* 210 */     content.add(tabs);
/* 211 */     setContentPane(content);
/*     */     
/* 213 */     pack();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getPreferredSize()
/*     */   {
/* 223 */     return PREFERRED_SIZE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private JTabbedPane createTabs(ProjectInfo info)
/*     */   {
/* 236 */     JTabbedPane tabs = new JTabbedPane();
/*     */     
/* 238 */     JPanel aboutPanel = createAboutPanel(info);
/* 239 */     aboutPanel.setBorder(STANDARD_BORDER);
/* 240 */     String aboutTab = this.resources.getString("about-frame.tab.about");
/*     */     
/* 242 */     tabs.add(aboutTab, aboutPanel);
/*     */     
/* 244 */     JPanel systemPanel = new SystemPropertiesPanel();
/* 245 */     systemPanel.setBorder(STANDARD_BORDER);
/* 246 */     String systemTab = this.resources.getString("about-frame.tab.system");
/*     */     
/* 248 */     tabs.add(systemTab, systemPanel);
/*     */     
/* 250 */     return tabs;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private JPanel createAboutPanel(ProjectInfo info)
/*     */   {
/* 265 */     JPanel about = new JPanel(new BorderLayout());
/*     */     
/* 267 */     JPanel details = new AboutPanel(this.application, this.version, this.copyright, this.info, this.logo);
/*     */     
/*     */ 
/* 270 */     boolean includetabs = false;
/* 271 */     JTabbedPane tabs = new JTabbedPane();
/*     */     
/* 273 */     if (this.contributors != null) {
/* 274 */       JPanel contributorsPanel = new ContributorsPanel(this.contributors);
/*     */       
/* 276 */       contributorsPanel.setBorder(STANDARD_BORDER);
/* 277 */       String contributorsTab = this.resources.getString("about-frame.tab.contributors");
/*     */       
/* 279 */       tabs.add(contributorsTab, contributorsPanel);
/* 280 */       includetabs = true;
/*     */     }
/*     */     
/* 283 */     if (this.licence != null) {
/* 284 */       JPanel licencePanel = createLicencePanel();
/* 285 */       licencePanel.setBorder(STANDARD_BORDER);
/* 286 */       String licenceTab = this.resources.getString("about-frame.tab.licence");
/*     */       
/* 288 */       tabs.add(licenceTab, licencePanel);
/* 289 */       includetabs = true;
/*     */     }
/*     */     
/* 292 */     if (info != null) {
/* 293 */       JPanel librariesPanel = new LibraryPanel(info);
/* 294 */       librariesPanel.setBorder(STANDARD_BORDER);
/* 295 */       String librariesTab = this.resources.getString("about-frame.tab.libraries");
/*     */       
/* 297 */       tabs.add(librariesTab, librariesPanel);
/* 298 */       includetabs = true;
/*     */     }
/*     */     
/* 301 */     about.add(details, "North");
/* 302 */     if (includetabs) {
/* 303 */       about.add(tabs);
/*     */     }
/*     */     
/* 306 */     return about;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private JPanel createLicencePanel()
/*     */   {
/* 317 */     JPanel licencePanel = new JPanel(new BorderLayout());
/* 318 */     JTextArea area = new JTextArea(this.licence);
/* 319 */     area.setLineWrap(true);
/* 320 */     area.setWrapStyleWord(true);
/* 321 */     area.setCaretPosition(0);
/* 322 */     area.setEditable(false);
/* 323 */     licencePanel.add(new JScrollPane(area));
/* 324 */     return licencePanel;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/ui/about/AboutDialog.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */