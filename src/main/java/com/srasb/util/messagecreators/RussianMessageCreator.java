package com.srasb.util.messagecreators;

public class RussianMessageCreator extends MessageCreator {
    @Override
    public String noCustomerWithIdMessage() {
        return "Клиента с таким ID не существует.";
    }

    @Override
    public String customerDeletedMessage() {
        return "Клиент был успешно удалён!";
    }

    @Override
    public String noSpaceWithIdMessage() {
        return "Пространства с таким ID не существует.";
    }

    @Override
    public String validationFailedMessage() {
        return "Ошибка валидации: ";
    }

    @Override
    public String spaceChangedMessage() {
        return "Пространство было успешно изменено!";
    }

    @Override
    public String spaceAddingErrorMessage() {
        return "Произошла ошибка при добавлении изменённого пространства в базу данных.";
    }

    @Override
    public String spaceAlreadyExistsMessage() {
        return "Пространство с таким названием уже существует!";
    }

    @Override
    public String spaceSavedMessage() {
        return "Пространство было успешно сохранено!";
    }

    @Override
    public String spaceDeletedMessage() {
        return "Пространство было успешно удалено!";
    }

    @Override
    public String spaceForReservationNotExists() {
        return "Выбранное для бронирования пространство не существует!";
    }

    @Override
    public String startTimeOverlapsMessage() {
        return "Время начала бронирования пересекается с существующим бронированием!";
    }

    @Override
    public String endTimeOverlapsMessage() {
        return "Время окончания бронирования пересекается с существующим бронированием!";
    }

    @Override
    public String reservationSavedMessage() {
        return "Бронирование было успешно сохранено!";
    }

    @Override
    public String noReservationWithIdMessage() {
        return "Бронирования с таким ID не существует.";
    }

    @Override
    public String noRightsToDeleteReservationMessage() {
        return "У вас нет прав на удаление этого бронирования.";
    }

    @Override
    public String reservationDeletedMessage() {
        return "Бронирование было успешно удалено!";
    }

    @Override
    public String sessionStartedAdminMessage() {
        return "Сессия начата для администратора.";
    }

    @Override
    public String sessionStartedCustomerMessage() {
        return "Сессия начата для клиента: ";
    }

    @Override
    public String noRegisteredUserWithNameMessage() {
        return "Пользователь с таким именем не зарегистрирован. Пожалуйста, зарегистрируйтесь.";
    }

    @Override
    public String customerExistsMessage() {
        return "Клиент с таким именем уже существует!";
    }

    @Override
    public String customerRegisteredMessage() {
        return "Клиент был зарегистрирован: ";
    }
}
