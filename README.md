## :pencil2: 컴퓨터공학과 20184494 박세영

## :paperclip: 목차

 [![Menul](https://img.shields.io/badge/go-documentation-blue.svg?style=flat-square)](https://iamfreeman.tistory.com/entry/vi-vim-%ED%8E%B8%EC%A7%91%EA%B8%B0-%EB%AA%85%EB%A0%B9%EC%96%B4-%EC%A0%95%EB%A6%AC-%EB%8B%A8%EC%B6%95%ED%82%A4-%EB%AA%A8%EC%9D%8C-%EB%AA%A9%EB%A1%9D)
 
1. [Add to quotes to ansible playbook](https://github.com/sey2/VimGolf/blob/master/README.md#crescent_moon-add-to-quotes-to-ansible-playbook)
2. [Simple replacements]()
3. [Satisfy the go linter]()
4. [Plotting Some variables in python]()
5. [Python dataclasses]()

# :crescent_moon: Add to quotes to ansible playbook
### 문제 1 <br>
<img src = "https://user-images.githubusercontent.com/54762273/143408719-6d1b996b-7b3c-4fa6-833b-5e75536750d1.PNG" width="600" hegiht="600" /> <br>

### 문제 풀이 <br>
#### ``G W i " <End> <Ctrl + @> ZZ ``  <br> 

![ezgif com-gif-maker](https://user-images.githubusercontent.com/54762273/143416276-15f395c6-d9ba-42bd-b92d-6a5861868532.gif)



### 문제 풀이 2 <br>
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

<h3> 문제 풀이 1 설명 </h3>

**위의 문제는 이동을 하여 편집만 하면 되는 간단한 문제 입니다.** <br>

**G**키를 이용해 문서 맨 아래 state의 s 글자 앞으로 커서를 옮긴 후 **W**를 이용해 { 의 커서 앞으로 이동합니다. <br>
**i**키를 사용하면 편집 모드로 바뀌고 커서 위치에 문자를 삽입할 수 있습니다. 이렇게 **i -> " 를 눌러 "{{ sss ... }} 로 만들어 주고**<br> 
<End> 키를 눌러 현재 라인의 끝의 커서로 옮겨 <Ctrl+@>를 이용해 "를 삽입하면 **"{{ sss ... }}"** 이 완성이 되고 명령 행 모드로 변경 됩니다.<br>
**:wq** 를 입력하여 vim 에디터를 빠져 나갈 수 있지만 이렇게 되면 keystroke가 3이 됩니다. <br>
keystroke를 2로 줄이는 방법은 **Ctrl + @** 를 입력 후에 바로 **ZZ**를 눌러주면 저장 후 종료 하게 됩니다. <br>
**이렇게 해서 총 점수는 8점으로 최고 점수가 나오게 됩니다.**
 
 <br>
 
 # :crescent_moon: Simple replacements
 ### 문제 2 <br>
 <img src = "https://user-images.githubusercontent.com/54762273/143541666-a2f2e64d-76c1-4eaa-9745-00d85ce32f45.PNG" width="600" hegiht="600" /> 

### 문제풀이 
#### ``L W i " <End> <Ctrl + @> ZZ`` <br>
 
