package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class Photo implements ValueObject {

    @Lob
    private final byte[] photo;

    public Photo(byte[] photo) {

        this.photo = photo;

    }

    public Photo() {
        this.photo = null;
    }

    public byte[] getPhoto() {
        return photo;
    }
}
