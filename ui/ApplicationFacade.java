public class ApplicationFacade {
 
    
    private HomeComponent hc;
    private SearchComponent sc;
    private NotificationScreenComponent nsc;
    private ProfileComponent pc;
    private LoginComponent lc;
    
    public ApplicationFacade(ImageManager im) {

        hc = new HomeComponent(im);
        sc = new SearchComponent(im);
        nsc = new NotificationScreenComponent(im);
        pc = new ProfileComponent(im);
        


    }
}
