package application.services;

import application.model.Toast;
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

}
