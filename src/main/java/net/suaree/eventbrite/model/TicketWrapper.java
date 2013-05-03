package net.suaree.eventbrite.model;

import java.util.AbstractList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Helper class to work around double-wrapped ticket data in event_search API results.
 *
 * @author roger
 */
final class TicketWrapper {
    private Ticket ticket;

    Ticket getTicket() {
        return ticket;
    }

    static List<Ticket> getTickets(final List<TicketWrapper> wrappers) {
        return new AbstractList<Ticket>() {
            @Override
            public Ticket get(int i) {
                return wrappers.get(i).getTicket();
            }

            @Override
            public int size() {
                return wrappers.size();
            }
        };
    }
}
