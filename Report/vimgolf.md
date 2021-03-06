## :pencil2: 컴퓨터공학과 20184494 박세영

## :paperclip: 목차

 [![Menul](https://img.shields.io/badge/go-documentation-blue.svg?style=flat-square)](https://iamfreeman.tistory.com/entry/vi-vim-%ED%8E%B8%EC%A7%91%EA%B8%B0-%EB%AA%85%EB%A0%B9%EC%96%B4-%EC%A0%95%EB%A6%AC-%EB%8B%A8%EC%B6%95%ED%82%A4-%EB%AA%A8%EC%9D%8C-%EB%AA%A9%EB%A1%9D)
 
1. [Add to quotes to ansible playbook](https://github.com/sey2/VimGolf/blob/master/README.md#crescent_moon-add-to-quotes-to-ansible-playbook)
2. [Simple replacements](https://github.com/sey2/VimGolf/blob/master/README.md#crescent_moon-simple-replacements)
3. [Satisfy the go linter](https://github.com/sey2/VimGolf/blob/master/README.md#crescent_moon-satisfy-the-go-linter)
4. [Plotting Some variables in python](https://github.com/sey2/VimGolf/blob/master/README.md#crescent_moon-plotting-some-variables-in-python)
5. [Python dataclasses](https://github.com/sey2/VimGolf/blob/master/README.md#crescent_moon-python-dataclasses)

# :crescent_moon: Add to quotes to ansible playbook
### 문제 1 <br>
<img src = "https://user-images.githubusercontent.com/54762273/143408719-6d1b996b-7b3c-4fa6-833b-5e75536750d1.PNG" width="600" hegiht="600" /> <br>
------------------
### 문제 풀이 1 <br>
#### ``GWi"<End><Ctrl + @>ZZ``  <br> 

![ezgif com-gif-maker](https://user-images.githubusercontent.com/54762273/143416276-15f395c6-d9ba-42bd-b92d-6a5861868532.gif)
------------------

### 문제 풀이 2 <br>
#### ``LWi"<End><Ctrl + @>ZZ`` <br>

![2](https://user-images.githubusercontent.com/54762273/143417923-cec4761a-4a9c-43bb-b188-62ab3e4a260b.gif)
------------------

:bulb: **커멘드 설명** 
|옵션|설명|
|:---:|:---:|
|G|문서 맨 아래로 이동 합니다. |
|W|공백 기준으로 다음(단어의 시작)으로 이동 합니다.|
|i|커서 위치에 문자를 삽입 합니다.|
|End|해당 라인의 맨 끝으로 이동 합니다. (편집 모드에서도 가능)|
|Ctrl+@|이전에 삽입된 텍스트를 삽입하고 편집 모드를 중지합니다.|
|L|현재 화면의 마지막 라인으로 이동|
|ZZ|저장 후 종료 (명령행 모드)| 
------------------

<h3>문제 풀이 1 설명</h3>

**위의 문제는 이동을 하여 편집만 하면 되는 간단한 문제 입니다.** <br>

**G키**를 이용해 문서 맨 아래 state의 s 글자 앞으로 커서를 옮긴 후 **W**를 이용해 { 의 커서 앞으로 이동합니다. <br>
**i**키를 사용하면 편집 모드로 바뀌고 커서 위치에 문자를 삽입할 수 있습니다. 이렇게 **i -> " 를 눌러 "{{ sss ... }} 로 만들어 주고**<br> 
End 키를 눌러 현재 라인의 끝의 커서로 옮겨 <Ctrl+@>를 이용해  삽입하면 **"{{ sss ... }}"** 이 완성이 되고 명령 행 모드로 변경 됩니다.<br>
**:wq** 를 입력하여 vim 에디터를 빠져 나갈 수 있지만 이렇게 되면 keystroke가 3이 됩니다. <br>
keystroke를 2로 줄이는 방법은 **Ctrl + @** 를 입력 후에 바로 **ZZ**를 눌러주면 저장 후 종료 하게 됩니다. <br>
**이렇게 해서 총 점수는 8점으로 최고 점수가 나오게 됩니다.**
 
 <h3> 문제 풀이 2 설명 </h3>
 
 **L**키를 사용하면 현재 화면의 마지막 라인으로 이동하기 때문에 G처럼 마지막 라인으로 이동하여서 문재 풀이1 처럼 접근하면 됩니다. 
 
 ------------------
 <br><br>
 
 # :crescent_moon: Simple replacements
 ### 문제 2 <br>
 <img src = "https://user-images.githubusercontent.com/54762273/143541666-a2f2e64d-76c1-4eaa-9745-00d85ce32f45.PNG" width="600" hegiht="600" /> 
 
------------------
 
### 문제풀이 
#### ``w*:s//vim/g<CR>)B*g&ZZ `` <br>
 
![문제2g](https://user-images.githubusercontent.com/54762273/143543159-062327f2-63b3-41ad-b38a-4b9f7339e76e.gif)
------------------ 
 
:bulb: **커멘드 설명** 
|옵션|설명|
|:---:|:---:|
|w|단어 첫글자 기준으로 다음으로 이동|
|*|현재 단어를 포워드 방향으로 찾기|
|:s/old/new/g |현재 행의 모든 old를 new로 교체|
|)|다음 문장의 첫 글자로 이동 |
|B| 이전 단어의 첫 글자로 이동|
|g& |가장 최근에 수행한 :s를 반복한다.|
|ZZ|저장 후 종료 (명령행 모드)|
------------------
 <h3> 문제 풀이 설명 </h3>
 
 **w**를 사용하게 되면 단어 첫글자 기준으로 다음으로 이동하게 되는데<br>
 **W**를 이용해 (커서)submline으로 이동한 후 *키를 눌러 submline단어를 모두 찾은 후 <br>
 스트링 치환 명령어인 :s를 이용하여 **:s//vim/g**를 치게 되면 <br>
 앞에서 찾은 submline단어가 선택되어 있기 때문에 submline단어가 vim으로 바뀌게 됩니다. <br>
 그 후 )를 사용하면 다음 문장의 첫 글자로 이동을 하게 되는데 2번째 행의 (커서)It was not 으로 이동하게 됩니다.<br>
 그 후 **B**를 사용하면 이전 단어의 첫 글자로 이동하는데 <br>
 2번째 문장의 (커서)emacs. 로 이동하게 됩니다. **참고로 소문자 b는 이전 단어의 마지막 글자로 이동합니다.** <br>
 그 상태에서 *을 하면 모든 emacs를 찾아 선택되고 **&g**를 사용하여 이전에 수행했던 :s 커맨드를 반복 수행 하여 emacs의 모든 단어를 vim으로 변경 후 ZZ를 이용하여 vim을 빠져 나오면<br>
 **최고 점수인 19점이 나오게 됩니다.**
<br><br>

# :crescent_moon: Satisfy the go linter
 
### 문제 3 <br>
<img src = "https://user-images.githubusercontent.com/54762273/143556659-57a976dd-b5e4-4414-af59-b57903439e02.PNG" width="600" hegiht="600" /> 
 
------------------
 
### 문제풀이 
#### ``Lqa-O// <C-N> TODO<Esc>q@aZZ``
 
![문제3g](https://user-images.githubusercontent.com/54762273/143557360-fc4881f4-397d-4476-accd-529d8879cc2f.gif)
------------------
 
:bulb: **커멘드 설명** 
|옵션|설명|
|:---:|:---:|
|L|현재 화면의 마지막 라인으로 이동|
|q|매크로 기록|
|-|이전 행으로 이동|
|O|행 위에 삽입 |
|Ctrl + n|자동 완성|
|@|매크로 실행|
|ZZ|저장 후 종료 (명령행 모드)|
------------------
 
<h3> 문제 풀이 설명 </h3>
 
**이 문제는 삽입하는 과정이 2번 반복되기 때문에 매크로를 이용하여 문제를 해결 할 수 있습니다.** <br><br>
**L**을 사용해 마지막 라인으로 이동한 후 **qa**를 입력해 이름 a인 매크로를 기록하기 시작합니다. <br>
5번 라인 위에 // Debug TODO를 입력하기 위해 -를 입력해 5번 라인으로 이동한 후 O를 입력해서 편집 모드로 변경 합니다. <br><br>
 
**Ctrl + N**을 입력하면 자동 완성 기능을 사용할 수 있는데 <br>
Debug문자가 자동으로 완성되어서 **Ctrl + N TODO**를 입력하면 // Debug TODO가 완성 됩니다. <br><br>
 
그 후 **Esc키**를 눌러 명령 행 모드로 진입 한 후 **q**를 누르면 매크로 기록이 중단이 되고 **@a**를 누르면 a매크로가 실행이 되고 <br>
기록해 놨던 **-O// <C-N> TODO가 반복 수행**이 되면 4번 라인에 // Version TODO가 입력 되고 **ZZ**를 눌러 vim을 빠져 나가면  <br>
**최고 점수인 20점이 나오게 됩니다.**
 
 ------------------
 <br><br>
                                    
 # :crescent_moon: Plotting Some variables in python
 
 ### 문제 4 <br>
<img src = "https://user-images.githubusercontent.com/54762273/143584035-26a400f2-581a-41e3-a2fb-7961a7bc058e.PNG" width="600" hegiht="600" /> 
 
------------------
 
### 문제풀이 
#### ``9w<C-V>#lcabs()<Esc>Pj<C-V>}g<C-A>W.0.fkrbjrrjrgZZ `` <br>
![문제4g](https://user-images.githubusercontent.com/54762273/143596204-50f5f013-47ba-460c-8e35-b36f29bc800f.gif)
 
------------------
 
:bulb: **커멘드 설명** 
|옵션|설명|
|:---:|:---:|
|w|다음 단어의 첫 글자로 이동|
|Ctrl+V|비주얼 블록 모드|
|#|현재 단어를 백워드 방향으로 찾기|
|ㅣ|커서를 앞으로 한칸 이동 |
|c|선택된 행 삭제|
|P|삭제된 행 현재 행 아래에 삽입|
|j|커서를 아래로 한칸 이동|
|}|문단 끝 으로 이동|
|.|이전 명령 반복|
|r|한 글자 교체|
|Ctrl+A|커서 다음에 위치한 숫자를 찾아서 1 증가 시킨다.|
|ZZ|저장 후 종료 (명령행 모드)|
------------------ 
<h3> 문제 풀이 설명 </h3>
 
**이 문제는 비주얼 블록 모드를 이용 하였는데 비주얼 블록 모드 키는 Ctrl + v 입니다.** <br>
**하지만 윈도우에서는 Ctrl + v가 붙혀넣기 키랑 중복이 되서 비주얼 모드 진입이 되지 않는 문제점이 있습니다.** <br>
**해결 방법은 Window Power Shell의 드롭다운을 클릭하면 설정이 보이는데 Shift키와 함께 설정키를 클릭하면** <br>
**settings.json 파일이 나오는데 { "command": "paste", "keys": "ctrl+v" }, 이 부분을 주석 처리 하면 됩니다.** <br><br>
 
**9w**를 입력하여 2번째 행의 (커서)y1으로 옮긴다.<br>
그 후 **Ctr + v** 키를 눌러 **비주얼 블록 모드**에 진입하는데 비주얼 블록 모드란 원하는 부분을 상자 처럼 선택 할 수 있습니다.<br><br>
#을 눌러서 **y1**을 전부 선택 한 후 **l**로 커서를 오른쪽으로 한칸 옮긴 후 **c**를 눌러 전부 삭제합니다.<br>
삭제가 되면 abs()를 입력하고 **Esc**키로 비주얼 블록 모드를 나오게 되면 #을 눌러 선택 된 부분이 전부 abs()로 채워집니다.<br>
그 후 **c**를 써서 삭제 했던 **y1** 문자를 **P**를 이용하여 다시 전부 채워 넣습니다.<br><br>
다시 한번 비주얼 블록 모드에 진입하여 **}g**로 문단 끝으로 이동하고 **Ctrl+A**를 이용해 숫자 하나를 증가시키고 <br>
나머지는 직접 이동 하며 **r**키를 이용해 숫자를 하나씩 바꿔줍니다. <br><br>
**이렇게 하면 최고 점수인 34점이 나오게 됩니다.**<br><br>
 
------------------ 
<br><br>
# :crescent_moon: python dataclasses
 
### 문제 5 <br>
<img src = "https://user-images.githubusercontent.com/54762273/143608019-f6f9ae63-023c-4b94-a4d2-a9bec527ca62.PNG" width="600" hegiht="600" /> 
 
------------------ 
 
### 문제풀이 
#### ``Gbas<C-N><C-N>,n<C-N>,a<C-N>,sc<C-N><Esc>ZZ`` <br>
 
![문제5g](https://user-images.githubusercontent.com/54762273/143608108-18d71283-2673-464e-a31e-f8dd402b3447.gif)
 
------------------ 
:bulb: **커멘드 설명** 
|옵션|설명|
|:---:|:---:|
|G|문서의 맨 마지막 행으로 이동|
|b|이전 단어로 이동|
|a|커서 위치 다음칸부터 입력|
|Ctrl + n|자동 완성|
|ZZ|저장 후 종료 (명령행 모드)|
------------------
 
<h3> 문제 풀이 설명 </h3>
 
**G**를 이용해 끝으로 이동한 다음 **b**키를 사용 하면 커서가 **filelds = (커서)""** 로 이동하게 되는데<br>
**a**키를 이용해 편집모드로 바꾸고 **Ctrl + n**을 사용하여 자동 완성 기능을 이용하면 더 쉽게 답을 완성 시킬 수 있습니다.<br>
**이렇게 하게 되면 최고 점수인 19점을 획득 할 수 있습니다.**
