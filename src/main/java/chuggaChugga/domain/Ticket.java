package chuggaChugga.domain;


import java.time.LocalDateTime;

public class Ticket {

    private String userFirstName;
    private String userLastName;
    private String trainName;
    private String departureStation;
    private String arrivalStation;
    private int cost;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;

    private Ticket(Builder builder) {
        userFirstName = builder.userFirstName;
        userLastName = builder.userLastName;
        trainName = builder.trainName;
        departureStation = builder.departureStation;
        arrivalStation = builder.arrivalStation;
        cost = builder.cost;
        departureDate = builder.departureDate;
        arrivalDate = builder.arrivalDate;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String userFirstName;
        private String userLastName;
        private String trainName;
        private String departureStation;
        private String arrivalStation;
        private int cost;
        private LocalDateTime departureDate;
        private LocalDateTime arrivalDate;

        private Builder() {
        }

        public Builder withUserFirstName(String val) {
            userFirstName = val;
            return this;
        }

        public Builder withUserLastName(String val) {
            userLastName = val;
            return this;
        }

        public Builder withTrainName(String val) {
            trainName = val;
            return this;
        }

        public Builder withDepartureStation(String val) {
            departureStation = val;
            return this;
        }

        public Builder withArrivalStation(String val) {
            arrivalStation = val;
            return this;
        }

        public Builder withCost(int val) {
            cost = val;
            return this;
        }

        public Builder withDepartureDate(LocalDateTime val) {
            departureDate = val;
            return this;
        }

        public Builder withArrivalDate(LocalDateTime val) {
            arrivalDate = val;
            return this;
        }

        public Ticket build() {
            return new Ticket(this);
        }
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public int getCost() {
        return cost;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }
}
