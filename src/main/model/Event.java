package model;

public class Event {
    private String description;

    /**
     * Creates an event with the given description
     * and the current date/time stamp.
     * @param description  a description of the event
     */

    public Event(String description) {

        this.description = description;
    }


    /**
     * Gets the description of this event.
     * @return  the description of the event
     **/
    public String getDescription () {
        return description;
    }

//    @Override
//    public boolean equals(Object other) {
//        if (other == null) {
//            return false;
//        }
//
//        if (other.getClass() != this.getClass()) {
//            return false;
//        }
//
//        Event otherEvent = (Event) other;
//
//        return (this.dateLogged.equals(otherEvent.dateLogged) &&
//                this.description.equals(otherEvent.description));
//    }

    @Override
    public int hashCode() {
        return 0; //stub
    }

    @Override
    public String toString() {
        return ""; //stub
    }
}
