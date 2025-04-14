package com.srasb.util.messagecreators;

public abstract class MessageCreator {

    public abstract String noCustomerWithIdMessage();

    public abstract String customerDeletedMessage();

    public abstract String noSpaceWithIdMessage();

    public abstract String validationFailedMessage();

    public abstract String spaceChangedMessage();

    public abstract String spaceAddingErrorMessage();

    public abstract String spaceAlreadyExistsMessage();

    public abstract String spaceSavedMessage();

    public abstract String spaceDeletedMessage();

    public abstract String spaceForReservationNotExists();

    public abstract String startTimeOverlapsMessage();

    public abstract String endTimeOverlapsMessage();

    public abstract String reservationSavedMessage();

    public abstract String noReservationWithIdMessage();

    public abstract String noRightsToDeleteReservationMessage();

    public abstract String reservationDeletedMessage();

    public abstract String sessionStartedAdminMessage();

    public abstract String sessionStartedCustomerMessage();

    public abstract String noRegisteredUserWithNameMessage();

    public abstract String customerExistsMessage();

    public abstract String customerRegisteredMessage();
}