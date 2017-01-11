package application.services;

import application.model.Toast;
import application.model.Toast.ToastFlavor;
import application.repositories.toast.ToastRepository;
import application.repositories.utils.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by matan,
 * On 04/01/2017.
 */

@Service
public class ToastService {

    @Autowired
    private ToastRepository toastRepository;

    @Autowired
    private RepositoryUtils repositoryUtils;

    public Toast getToast(String toastId) {
        Toast toast = repositoryUtils.validateObjectExist(Toast.class, toastId);
        return toast;
    }

    public Toast createToast(String userId, String eventId, ToastFlavor toastFlavor, String giftPresenters, String videoUrl, String imageUrl, String text) {
        Toast toast = new Toast(userId, eventId, toastFlavor, giftPresenters, videoUrl, imageUrl, text);
        toastRepository.save(toast);
        return toast;
    }

    public Toast createToast(String userId, String eventId, String toastFlavorString, String giftPresenters, String videoUrl, String imageUrl, String text) {
        ToastFlavor toastFlavor = ToastFlavor.valueOf(toastFlavorString);
        return createToast(userId, eventId, toastFlavor, giftPresenters, videoUrl, imageUrl, text);
    }

}
