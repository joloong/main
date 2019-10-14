package seedu.address.model.transaction;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * API of Transaction.
 */
public abstract class Transaction {

    protected Amount amount;
    protected Date date;
    protected Person peopleInvolved;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    public Transaction(Amount amount, Date date) {
        this.amount = amount;
        this.date = date;
        // TODO: remove this stub
        this.peopleInvolved = new Person(
                new Name("Alex"),
                new Phone("82831821"),
                new Email("person@gmail.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                new HashSet<Tag>());;
    }

    public Transaction(Amount amount, Date date, Person personInvolved) {
        this(amount, date);
        this.peopleInvolved = personInvolved;
    }

    public Amount getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public Person getPeopleInvolved() {
        return peopleInvolved;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public abstract Amount handleBalance(Amount balance);

    @Override
    public abstract boolean equals(Object obj);
}
