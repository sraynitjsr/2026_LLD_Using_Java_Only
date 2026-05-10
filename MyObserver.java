import java.util.ArrayList;
import java.util.List;

public class MyObserver {

    // Subject interface
    interface Subject {
        void attach(Observer observer);
        void detach(Observer observer);
        void notifyObservers();
    }

    // Observer interface
    interface Observer {
        void update(String message);
    }

    // Concrete Subject
    static class NewsAgency implements Subject {
        private List<Observer> observers = new ArrayList<>();
        private String news;

        @Override
        public void attach(Observer observer) {
            observers.add(observer);
        }

        @Override
        public void detach(Observer observer) {
            observers.remove(observer);
        }

        @Override
        public void notifyObservers() {
            for (Observer observer : observers) {
                observer.update(news);
            }
        }

        public void setNews(String news) {
            this.news = news;
            notifyObservers();
        }

        public String getNews() {
            return news;
        }
    }

    // Concrete Observer 1
    static class NewsChannel implements Observer {
        private String name;
        private String news;

        public NewsChannel(String name) {
            this.name = name;
        }

        @Override
        public void update(String news) {
            this.news = news;
            System.out.println(name + " received news: " + news);
        }

        public String getNews() {
            return news;
        }
    }

    // Concrete Observer 2
    static class MobileApp implements Observer {
        private String appName;
        private String news;

        public MobileApp(String appName) {
            this.appName = appName;
        }

        @Override
        public void update(String news) {
            this.news = news;
            System.out.println(appName + " pushed notification: " + news);
        }

        public String getNews() {
            return news;
        }
    }

    public static void Start() {
        System.out.println("\n=== Observer Pattern ===");

        // Create subject
        NewsAgency agency = new NewsAgency();

        // Create observers
        NewsChannel channel1 = new NewsChannel("CNN News");
        NewsChannel channel2 = new NewsChannel("BBC News");
        MobileApp app1 = new MobileApp("News App");

        // Attach observers to subject
        agency.attach(channel1);
        agency.attach(channel2);
        agency.attach(app1);

        // Publish news - all observers will be notified
        System.out.println("\nPublishing first news:");
        agency.setNews("Breaking: New technology breakthrough announced!");

        // Detach one observer
        System.out.println("\nDetaching BBC News...");
        agency.detach(channel2);

        // Publish another news - only remaining observers will be notified
        System.out.println("\nPublishing second news:");
        agency.setNews("Update: Market reaches all-time high!");

        // Attach a new observer
        System.out.println("\nAdding new observer...");
        MobileApp app2 = new MobileApp("Breaking News Widget");
        agency.attach(app2);

        // Publish final news
        System.out.println("\nPublishing third news:");
        agency.setNews("Alert: Important policy change effective immediately!");
    }
}
