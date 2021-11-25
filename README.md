## :pencil2: 컴퓨터공학과 20184494 박세영

## :paperclip: 목차

1. [Add to quotes to ansible playbook](https://github.com/sey2/VimGolf/blob/master/README.md#crescent_moon-add-to-quotes-to-ansible-playbook)
2. [Simple replacements]()
3. [Satisfy the go linter]()
4. [Plotting Some variables in python]()
5. [Python dataclasses]()

# :crescent_moon: Add to quotes to ansible playbook
### 문제 1 <br>
<img src = "https://user-images.githubusercontent.com/54762273/143408719-6d1b996b-7b3c-4fa6-833b-5e75536750d1.PNG" width="600" hegiht="600" /> <br>

### 문제 접근 1 <br>
#### ``G W i " <End> <Ctrl + @> ZZ ``  <br> 

![ezgif com-gif-maker](https://user-images.githubusercontent.com/54762273/143416276-15f395c6-d9ba-42bd-b92d-6a5861868532.gif)



### 문제 접근 2 <br>
#### ``L W i " <End> <Ctrl + @> ZZ`` <br>

![2](https://user-images.githubusercontent.com/54762273/143417923-cec4761a-4a9c-43bb-b188-62ab3e4a260b.gif)

**커멘드 설명** 
|옵션|설명|
|:---:|:---:|
|G|문서 맨 아래로 이동 합니다. |
|W|공백 기준으로 다음(단어의 시작)으로 이동 합니다.|
|i|커서 위치에 문자를 삽입 합니다.|
|End|해당 라인의 맨 끝으로 이동 합니다. (편집 모드에서도 가능)|
|Ctrl+@|이전에 삽입된 텍스트를 삽입하고 편집 모드를 중지합니다.|
|L|현재 화면의 마지막 라인으로 이동|
|ZZ|저장 후 종료 (명령행 모드)|

<h3> 문제 접근 설명 </h3>
위의 문제는 최소한의 글자수롤 이동을하여 편집하는것이다. 

**문제 접근1** 방식은 G키를 이용해 문서 맨 아래 state의 s 글자 앞으로 커서를 옮긴 후 W를 이용해 { 의 커서 앞으로 이동하는 것이다. <br>
i키를 사용하면 편집 모드로 바뀌고 커서 위치에 문자를 삽입할 수 있습니다. 이렇게 i -> " 를 눌러 "{{ sss ... }} 로 만들어 주고 <br> 
<End> 키를 눌러 현재 라인의 끝의 커서로 옮겨 <Ctrl+@>를 이용해 "를 삽입하면 "{{ sss ... }}"이 완성이 되고 명령 행 모드로 변경 됩니다. <br>
저장 후 나가는 방식은 :wq 를 입력하여 vim 에디터를 빠져 나갈 수 있지만 이렇게 되면 keystroke가 3이 됩니다. <br>
keystroke를 2로 줄이는 방법은 Ctrl + @ 를 입력 후에 바로 ZZ를 눌러주면 저장 후 종료 하게 됩니다. <br>
이렇게 해서 총 점수는 8점으로 최고 점수가 나오게 됩니다.
 
