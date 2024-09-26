// document.getElementById('new-task-form').addEventListener('submit', function(event) {
//     event.preventDefault();
//
//     const taskInput = document.getElementById('new-task-input');
//     const taskText = taskInput.value.trim();
//
//     if (taskText === '') return;
//
//     const taskList = document.getElementById('task-list');
//
//     // Создаем новый элемент задачи
//     const newTask = document.createElement('li');
//     newTask.innerHTML = `
//         <input type="checkbox" class="task-checkbox">
//         <label>${taskText}</label>
//         <button class="delete-btn">Delete</button>
//     `;
//
//     taskList.appendChild(newTask);
//     taskInput.value = '';
//
//     // Добавляем обработчик удаления задачи
//     newTask.querySelector('.delete-btn').addEventListener('click', function() {
//         taskList.removeChild(newTask);
//     });
//
//     // Добавляем обработчик для checkbox
//     const checkbox = newTask.querySelector('.task-checkbox');
//     checkbox.addEventListener('change', function() {
//         newTask.classList.toggle('done');
//     });
// });
