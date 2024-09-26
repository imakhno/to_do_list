document.getElementById('new-task-form').addEventListener('submit', function(event) {
    event.preventDefault(); // Останавливаем стандартное поведение формы

    const taskName = document.getElementById('new-task-input').value;

    // Подготавливаем данные для отправки на сервер
    const data = new FormData();
    data.append('name', taskName);

    // Создаем AJAX-запрос
    const xhr = new XMLHttpRequest();
    xhr.open('POST', '/add-new-task', true);
    xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');

    // Обработка ответа от сервера
    xhr.onload = function() {
        if (xhr.status === 200) {
            const task = JSON.parse(xhr.responseText); // Предполагаем, что сервер возвращает созданную задачу в JSON

            // Добавляем новую задачу в список
            const taskList = document.getElementById('task-list');
            const newTaskItem = document.createElement('li');

            newTaskItem.innerHTML = `
                <form action="/update-task-status" method="post">
                    <input type="hidden" name="taskId" value="${task.id}">
                    <input type="checkbox" name="done" value="true" 
                           class="task-checkbox" onchange="this.form.submit();">
                    <input type="hidden" name="done" value="false"/>
                </form>
                <label class="${task.done ? 'done' : ''}">${task.name}</label>
                <form method="post" action="/delete-task">
                <input type="hidden" name="taskId" th:value="${task.id}"/>
                <button class="delete-btn" th:data-task-id="${task.id}">Delete</button>
                </form>
            `;

            // Добавляем новую задачу в начало списка
            taskList.insertBefore(newTaskItem, taskList.firstChild);


            // Очищаем поле ввода
            document.getElementById('new-task-input').value = '';
        } else {
            console.error('Error:', xhr.statusText);
        }
    };

    // Отправляем данные
    xhr.send(data);
});

document.addEventListener('DOMContentLoaded', function() {
    // Получаем все кнопки удаления
    const deleteButtons = document.querySelectorAll('.delete-btn');

    deleteButtons.forEach(button => {
        button.addEventListener('click', function(event) {
            event.preventDefault(); // Предотвращаем стандартное поведение кнопки

            // Получаем ID задачи из атрибута data-task-id
            const taskId = this.getAttribute('data-task-id');

            // AJAX-запрос для удаления
            fetch(`/delete-task`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'X-Requested-With': 'XMLHttpRequest'
                },
                body: `taskId=${encodeURIComponent(taskId)}` // Передаем ID задачи
            })
                .then(response => {
                    if (response.ok) {
                        // Удаляем элемент задачи из списка
                        this.closest('li').remove();
                    } else {
                        console.error('Ошибка при удалении задачи:', response.statusText);
                    }
                })
                .catch(error => {
                    console.error('Произошла ошибка:', error);
                });
        });
    });
});


