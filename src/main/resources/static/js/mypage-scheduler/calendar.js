window.onload = function () {
  let today = new Date();
  const calendarBody = document.querySelector('.calendar-body');
  const prevEl = document.querySelector('.prev');
  const nextEl = document.querySelector('.next');
  // const inputBox = document.querySelector('.input-box');
  const inputBtn = document.querySelector('.input-btn');
  // const inputList = document.querySelector('.todoList');
  const showList = document.querySelector('.showList');
  const listText = document.querySelector('.listText');
  const createDate = document.querySelector('.createDate');
  const bgblack = document.querySelector('.bgblack');
  const closedBtn = document.querySelector('.closed');
//
//  // dialog를 사용하기 위한 준비
//  const modalDialog = document.querySelector('dialog');

  const calendarModal = document.querySelector('.calendar-modal');
  const calendarCellClick = document.querySelector('.dayHeader');

  let currentDate;

  buildCalendar();

  function buildCalendar() {
    let firstDate = new Date(today.getFullYear(), today.getMonth(), 1);
    const monthList = [
      'January',
      'February',
      'March',
      'April',
      'May',
      'June',
      'July',
      'August',
      'September',
      'October',
      'November',
      'December',
    ];
    const leapYear = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    const notLeapYear = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    const headerYear = document.querySelector('.current-year-month');
    // 윤년 체크하기
    if (firstDate.getFullYear() % 4 === 0) {
      pageYear = leapYear;
    } else {
      pageYear = notLeapYear;
    }
    headerYear.innerHTML = `${
      monthList[firstDate.getMonth()]
    }&nbsp;&nbsp;&nbsp;&nbsp;${today.getFullYear()}`;
    makeElement(firstDate);
    // showMain();
    currentDateget();
    // resetInsert();
  }

  function makeElement(firstDate) {
    let weekly = 100;
    let dateSet = 1;
    for (let i = 0; i < 6; i++) {
      let weeklyEl = document.createElement('div');
      weeklyEl.setAttribute('class', weekly);
      weeklyEl.setAttribute('id', 'weekly');
      for (let j = 0; j < 7; j++) {
        // i === 0이여야 하는 이유는 첫 날짜를 찍고 그 다음 날짜가 0번째 칸부터 다시 그려져야 하기 때문
        // firstDate.getMonth() => 현재 달의 일수가 몇일인지 반환해주고, 이 조건은 반환 된 값에 따라 출력해 준 후, 달력 출력 종료조건이다.
        if (
          (i === 0 && j < firstDate.getDay()) ||
          dateSet > pageYear[firstDate.getMonth()]
        ) {
          // 만약 해당 칸에 날짜가 없으면 div엘리먼트만 생성한다.
          let dateEl = document.createElement('div');
          weeklyEl.appendChild(dateEl);
        } else {
          // 해당 칸에 날짜가 있으면 div엘리먼트 생성 후 해당 날짜 넣어주기
          let dateEl = document.createElement('div');
          dateEl.textContent = dateSet;
          dateEl.setAttribute('class', dateSet);
          dateEl.setAttribute('id', `${today.format2()}-${dateSet}`);
          weeklyEl.appendChild(dateEl);
          dateSet++;
        }
      }
      weekly++;
      calendarBody.appendChild(weeklyEl);
    }
    // 현재 내가 선택한 날짜가 있으면 이전 달, 다음 달로 넘어가도 화면에 보여주기 위해 써줌
    let clickedDate = document.getElementsByClassName(today.getDate());
    clickedDate[0].classList.add('active');
  }

  function removeCalendar() {
    let divEls = document.querySelectorAll('.calendar-body > #weekly > div');
    for (let i = 0; i < divEls.length; i++) {
      divEls[i].remove();
    }
  }

  // calendar를 선택했을 때ㅡ
  // function choiceDate()

  // modal에 클릭한 날짜 업데이트 하기.
  // function showCalendar() {}

  // 왼쪽에 현재 날짜 업데이트 해주기.
  // function showMain() {
  //   const mainDay = document.querySelector('.main-day');
  //   const mainDate = document.querySelector('.main-date');
  //   const dayList = [
  //     'Sunday',
  //     'Monday',
  //     'Tuesday',
  //     'Wednesday',
  //     'Thursday',
  //     'Friday',
  //     'Saturday',
  //   ];
  //   mainDay.innerHTML = dayList[today.getDay()];
  //   mainDate.innerHTML = today.getDate();
  // }

  prevEl.addEventListener('click', function () {
    today = new Date(
      today.getFullYear(),
      today.getMonth() - 1,
      today.getDate(),
    );
    removeCalendar();
    buildCalendar();
    // resetInsert();
    redrawLi();
  });
  nextEl.addEventListener('click', function () {
    today = new Date(
      today.getFullYear(),
      today.getMonth() + 1,
      today.getDate(),
    );
    removeCalendar();
    buildCalendar();
    // resetInsert();
    redrawLi();
  });

  function currentDateget() {
    // format()을 이용해서 현재 날짜를 보기좋게 출력해주기 위해 사용.
    currentDate = today.format();
  }

  // 달력에서 해당 날짜를 click할 경우, modal창이 뜨면서 click한 날짜를 받아온다.
  // modal은 bootstrap을 사용한다.
  // JavaScript 코드

  // modal 창 열기
  calendarBody.addEventListener('click', function (e) {
    let target = e.target;
    let eachDate = document.querySelectorAll('.calendar-body > #weekly > div');

    if (e.target.innerHTML === '') return;

    for (let i = 0; i < eachDate.length; i++) {
      eachDate[i].classList.remove('active');
    }

    target.classList.add('active');
    today = new Date(today.getFullYear(), today.getMonth(), target.innerHTML);
    // showMain();
    currentDateget();
    redrawLi();
    // resetInsert();

    // modal창 열기
    let modal = new bootstrap.Modal(document.getElementById('myModal'), {});
    modal.show();
  });

  // // inputBox html 확인필요
  // inputBtn.addEventListener('click', function (e) {
  //   e.preventDefault();
  //   let inputValue = inputBox.value;
  //   insertTodo(inputValue);
  // });

  function insertTodo(text) {
    let inTodoObj = {
      todo: text,
    };
    if (!DATA[currentDate]) {
      DATA[currentDate] = [];
      DATA[currentDate].push(inTodoObj);
    } else {
      DATA[currentDate].push(inTodoObj);
    }
    const liEl = document.createElement('li');
    const spanEl = document.createElement('span');
    const delBtn = document.createElement('button');
    delBtn.innerText = 'DEL';
    delBtn.setAttribute('class', 'del-data');
    spanEl.innerHTML = text;
    liEl.appendChild(spanEl);
    liEl.appendChild(delBtn);
    // inputList.appendChild(liEl);
    liEl.setAttribute('id', DATA[currentDate].length);
    delBtn.addEventListener('click', delWork);
    liEl.addEventListener('dblclick', showTodo);
    // todoObj에 id값을 114번 줄에서 넣어주면 DATA[currentDate].length 값을 찾아올 수 없기 때문에 push해준 후 에 추가하여 local에 저장한다.
    inTodoObj.id = DATA[currentDate].length;
    save();

    // inputBox html 확인필요
    // inputBox.value = '';
  }

  function redrawLi() {
    // 다른 날짜를 클릭했을때 그 전에 작성한 totolist목록을 먼저 다 지우기 위해 li와 span을 찾아와 for문으로 지워주고 다시 그려준다.
    let liEl = document.querySelectorAll('LI');
    // for (let i = 0; i < liEl.length; i++) {
    //   inputList.removeChild(liEl[i]);
    // }
    for (let todoList in DATA) {
      if (todoList === currentDate) {
        for (let i = 0; i < DATA[todoList].length; i++) {
          const liEl2 = document.createElement('li');
          const spanEl2 = document.createElement('span');
          const delBtn2 = document.createElement('button');
          delBtn2.innerText = 'DEL';
          delBtn2.setAttribute('class', 'del-data');
          spanEl2.innerHTML = DATA[todoList][i].todo;
          liEl2.appendChild(spanEl2);
          liEl2.appendChild(delBtn2);
          // inputList.appendChild(liEl2);
          liEl2.setAttribute('id', DATA[todoList][i].id);
          delBtn2.addEventListener('click', delWork);
          liEl2.addEventListener('dblclick', showTodo);
        }
      }
    }
  }

  // // 로드될 때, 리셋
  // // 다음달,이전달 다른날, 첫 로드 될 때 마다 todo 목록이 있으면(if로 조건문 처리) 다 지우고 다시 그려주는 함수
  // function resetInsert() {
  //   const storeObj = localStorage.getItem(currentDate);

  //   if (storeObj !== null) {
  //     const parsed = JSON.parse(storeObj);
  //     const inputList = document.getElementById('todo-list');
  //     // 이전 작업 항목을 모두 삭제합니다.
  //     inputList.innerHTML = '';

  //     parsed.forEach(function (todo) {
  //       if (todo) {
  //         let liTag = document.createElement('li');
  //         let spanTag = document.createElement('spanspan');
  //         let deleteBtn = document.createElement('button');

  //         deleteBtn.setAttribute('class', 'del-data');
  //         deleteBtn.innerText = 'DEL';

  //         liTag.setAttribute('id', todo.id);

  //         spanTag.innerHTML = todo.todo;

  //         liTag.appendChild(spanTag);
  //         liTag.appendChild(delBtn);
  //         inputList.appendChild(liTag);

  //         deleteBtn.addEventListener('click', delWork);
  //         liTag.addEventListener('dblclick', showTodo);
  //       }
  //     });
  //   }
  // }
  // resetInsert();

  // function delWork(e) {
  //   e.preventDefault();
  //   let delParentLi = e.target.parentNode;
  //   // inputList.removeChild(delParentLi);

  //   // DATA[currentDate]를 filter함수를 이용해 todo로 돌면서 todo의 아이디값과 현재 내가 누른 아이디값이 같지 않은 것을 배열에 담아 리턴해주어서
  //   // 내가 지우고자 하는 요소를 뺀 나머지 요소를 배열에 담아 리턴해준다.
  //   // 그 배열을 다시 DATA[currentDate]에 할당하여 save();를 통해 localStorage에 넣어준다.
  //   const cleanToDos = DATA[currentDate].filter(function (todo) {
  //     return todo.id !== parseInt(delParentLi.id);
  //   });
  //   DATA[currentDate] = cleanToDos;
  //   save();
  // }

  // function showTodo(e) {
  //   showList.style.display = 'block';
  //   bgblack.style.display = 'block';
  //   listText.textContent = e.target.textContent;
  //   createDate.textContent = currentDate;
  // }

  // closedBtn.addEventListener('click', function (e) {
  //   showList.style.display = 'none';
  //   bgblack.style.display = 'none';
  // });

  // function save() {
  //   localStorage.setItem(currentDate, JSON.stringify(DATA[currentDate]));
  // }
};
