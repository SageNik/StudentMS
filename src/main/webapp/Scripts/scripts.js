/**
 * Created by Ник on 20.05.2016.
 */

function progressTest(checkboxes) {

    var count = 0;
    if (checkboxes.length>=1) {
        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                count++;
            }
        }
    } else {
        if (checkboxes.checked) {
            count++;
        }
    }
    if (count === 0) {
        alert("Выберите студента");
        return false;
    } else {
        if (count > 1) {
            alert("Выберите только одного студента");
            return false;
        } else {
            if (count === 1) return true;
        }
    }
}

function progresstDel(checkboxes) {

    var count = 0;
    if (checkboxes.length>=1) {
        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                count++;
            }
        }
    } else {
        if (checkboxes.checked) {
            count++;
        }
    }
    if (count === 0) {
        alert("Выберите одного или несколько студентов для удаления");
        return false;
    } else
        return true;
}

function discTest(checkboxes) {

    var count = 0;
    if (checkboxes.length>=1) {
        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                count++;
            }
        }
    } else {
        if (checkboxes.checked) {
            count++;
        }
    }
    if (count === 0) {
        alert("Выберите дисциплину");
        return false;
    } else {
        if (count > 1) {
            alert("Выберите только одну дисциплину");
            return false;
        } else {
            if (count === 1) return true;
        }
    }
}

function loginTest(username, password) {

    if (username.value === "") {
        alert("Введите логин");
        return false;
    }
    else {
        if (password.value === "") {
            alert("Введите пароль");
            return false;
        }
        else
            return true;
    }
}

function markTest(mark_dis) {
    if(mark_dis.length >= 1) {
        var count = 0;
        for (var i = 0; i < mark_dis.length; i++) {
            var value = mark_dis[i].value;

            if (value == '0' || value == '1' || value == '2' || value == '3' || value == '4' || value == '5' || value == '6'
                || value == '7' || value == '8' || value == '9' || value == '10' || value == '11' || value == '12') {
                count++;
            }
        }
        if (count == mark_dis.length) {
            return true;
        } else {
            alert("Ячейки не должны быть пустыми, а значения в них - меньше 0 и больше 12 ");
            return false;
        }
    }else{
        var value = mark_dis.value;

        if (value == '0' || value == '1' || value == '2' || value == '3' || value == '4' || value == '5' || value == '6'
            || value == '7' || value == '8' || value == '9' || value == '10' || value == '11' || value == '12') {
            return true
        }else{
            alert("Ячейка не должна быть пустой, а значение в ней - меньше 0 и больше 12 ");
            return false;
        }
    }
}

function selectTerm(bool) {

    var val = bool.value;
    if (val == '0') {
        alert("Подтвердите выбор семестра, нажатием кнопки 'выбрать'");
        return false;
    } else {
        if (val == '1') return true;
    }
}