package project.shimozukuri.banking.mappers;

public interface Mappable<E, D> {

    D toDto(E entity);

    E toEntity(D dto);
}
