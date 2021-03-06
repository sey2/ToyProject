## :pencil2: 컴퓨터공학과 20184494 박세영

## :paperclip: 목차

1. [getopt, getopts를 들어가기 전에](https://github.com/sey2/getopt_getopts_sed_awk/blob/master/README.md#crescent_moon-getopt-getopts%EB%A5%BC-%EB%93%A4%EC%96%B4%EA%B0%80%EA%B8%B0-%EC%A0%84%EC%97%90)<br>
2. [getopt](https://github.com/sey2/getopt_getopts_sed_awk/blob/master/README.md#crescent_moon-getopt) <br>
  1.[getopt 기본 규칙](#기본-규칙)<br>
  2.[예제](#예제)<br>
4. [getopts](https://github.com/sey2/getopt_getopts_sed_awk/blob/master/README.md#crescent_moon-getopts)<br>
  1.[예제](#직접-작성한-tmpsh-예제)
6. [sed](https://github.com/sey2/getopt_getopts_sed_awk/blob/master/README.md#crescent_moon-sed)<br>
  1.[옵션](https://github.com/sey2/getopt_getopts_sed_awk/blob/master/README.md#%EC%B0%B8%EA%B3%A0%EB%A1%9C-outputstream%EC%9D%80-stout%EC%9D%B4%EA%B3%A0-%EC%BD%98%EC%86%94%ED%99%94%EB%A9%B4%EC%9E%85%EB%8B%88%EB%8B%A4)<br>
  2.[예제](https://github.com/sey2/getopt_getopts_sed_awk/blob/master/README.md#%EC%B0%B8%EA%B3%A0%EB%A1%9C-outputstream%EC%9D%80-stout%EC%9D%B4%EA%B3%A0-%EC%BD%98%EC%86%94%ED%99%94%EB%A9%B4%EC%9E%85%EB%8B%88%EB%8B%A4)<br>
8. [awk](https://github.com/sey2/getopt_getopts_sed_awk/blob/master/README.md#crescent_moon-awk)<br>
  1.[옵션](https://github.com/sey2/getopt_getopts_sed_awk/blob/master/README.md#%EC%98%B5%EC%85%98-bulb)<br>
  2.[예제](https://github.com/sey2/getopt_getopts_sed_awk/blob/master/README.md#%EC%98%88%EC%A0%9C-2)<br>

# :crescent_moon: getopt, getopts를 들어가기 전에

보통 명령어들은 이러한 형식으로 사용 합니다.<br><br>
![예시](https://user-images.githubusercontent.com/54762273/141607935-f5bc8131-d55f-4fcf-bd81-aa3c11b44769.PNG)

### option 
아래와 같이 Linux에서 '-'로 시작하는 문자열을 의미합니다. <br>
`$./getopt -a`
`$./getopt -b optarg`
`$./getopt -optarg nonopt` <br><br>
 '-a', '-b' 가 옵션이고 '-'뒤에 오는 'a'와 'b'는 option character라고 부릅니다. <br>
 option은 option argument를 지정할 수 있는데, 바로 두 번째 예시에서의 '-b'의 뒤에 오는 **optarg**가 **option argument**입니다.<br><br>
 
 ### optstring
 optstring은 option character를 나타내는 문자열입니다.<br>
 `const char * optsring = "ab:";`<br>
 여기에서 'a'와 'b'는 option character이고, '-b'는 ':'를 이용하여 option argument를 표시합니다.<br><br>
 
 ```shell
 
$./command -a                #OK
$./command -a bbb            #OK bbb is non_option argument
$./command -accc             #error
$./command -abbb             #OK b is treated as 'b', bb is option argument
$./command -b                #Error option requires an argument
$./command -b bbb            #OK bbb is option argument
$./command -bbbb             #OK bbb is option argument
$./command -c                #error
``` 
<br>

### optarg
**getopt**가 option argument를 갖는 option을 해석하면 ex) ls -l /home/oss<br>
**optarg**에 **option argument**를 가르키는 포인터가 설정됩니다. <br>
즉, optarg를 참조하는 것으로 option argument를 취할 수 있습니다. (옵션 인자<문자열> 을 가르키는 포인터)


# :crescent_moon: getopt

[![Menul](https://img.shields.io/badge/go-documentation-blue.svg?style=flat-square)](https://man7.org/linux/man-pages/man3/getopt.3.html)

#### shell script로 프로그램을 만들다 보면 실행 인자 및 옵션을 필요로 하는 경우가 많다.<br>
프로그램을 실행하면서 컴파일러처럼 다양한 옵션을 인수로 받는다면 각 옵션별로 존재하는지,<br>
옵션에따라 지정된 인수가 있는지 확인할때 getopt()를 사용하면 편리합니다.<br>

- **getopt를 사용하는 이유**
  - 다양한 입력 값이 존재할 경우 사용자와 개발자의 편의를 보장
  - 스크립트를 보다 체계적으로 관리 할 수 있기 때문


리눅스 시스템에서는 명령어에 옵션을 사용할때 ***ls -al "/home/oss"*** 와 같이'-'을 이용해 본 경험이 있으실 겁니다. <br>
이러한 옵션을 분석할수 있게 제공하는 시스템 호출이 **getopt** 함수입니다.<br>
만약 ‘a’, ‘l’ 옵션을 전달하고자 한다면 “al”이라고 전달합니다.<br>
그리고 옵션 뒤에 ls -l <argument>와 같이 인자를 사용 한다면 l: 과 같이 뒤에 ':'을 붙혀주면 되고<br>
인자를 사용하지 않는다면 l과 같이 그대로 써주면 됩니다. <br>
 
 # 함수 헤더
 ```c 
 #include <unist.h>
 ```
 
 # 함수 원형
 ```c
int getopt(int argc, char *const argv[], const char *optstring); 
 ```

 
 # getopt return value <br>
 getopt의 처리가 성공적으로 완수하면 **option character**를 return하고,<br>
 **option argument**가 존재하면 **optarg**에 셋팅합니다. <br>
 error 발생시 **'?'** 를 return <br>
 다음으로 처리할 option이 없을 경우 -1을 return
 
 # 에러 발생 원인
 - '-'로 시작하는 문자열이지만 **option character**가 없는 경우 **ex) ls -tt <br>**
 - **option argument**를 갖는 option이지만 option character가 없는 경우 **ex) mkdir "non_argument"**
  
# 역사
getopt는 1980년 이전에 출시 되었고,<br>
getopt는 1985년 UNIFORUM 회의에서 usistd.h 헤더 파일의 일부로 POSUIX.2 표준에 지정이 되었습니다.<br>
또한 getopt는 시스템 종속 함수(시스템 콜 함수)이자 C라이브러리의 함수입니다.
 
 # 기본 규칙
 **getopt** 함수로 옵션을 처리하려면, 유닉스 명령에 대한 Basic Utility Syntax GuidLine을 준수해서 명령행 인자를 입력해야 한다.<br>
 1) 옵션의 이름은 한 글자이여야한다.
 2) 모든 옵션의 앞에는 하이픈이 있어야한다.
 3) 인자가 없는 옵션은 하나의 - 다음에 묶여서 올 수 있다. (-xvf)
 4) 옵션의 첫번째 인자는 공백이나 탭으로 띄고 입력해야 한다. (-l /tmp)
 5) 인자가 있어야하는 옵션에서 인자를 생략할 수 없다.
 6) 명령행에서 모든 옵션은 명령의 인자보다 앞에 와야한다. ( ls -l /tmp)
 
 
# 예제 
**직접 작성한 예제 example.sh** 
```shell
#!/bin/bash


# set 명령문의 이중 대시(--) 의미는 명령행 매개 변수를 getopt 명령문의 optstring 형식으로 대체하라는 것.

set -- $(getopt -q abcd: "$@")
  
while [ -n "$1" ]
do
        case "$1" in
                -a) echo "Apple Eating" ;;
                -b) echo "Banana Eating" ;;
                -c) echo "Cherry Eating" ;;
                -d) arg=$2
                    echo "$arg Eating" ;;
        esac
        shift
done
```
**실행결과** <br>
![example](https://user-images.githubusercontent.com/54762273/141613028-e12e7438-1233-477f-b97f-165f27c33ee7.PNG)
 
# :crescent_moon: getopts
 [![Menul](https://img.shields.io/badge/go-documentation-blue.svg?style=flat-square)](https://man7.org/linux/man-pages/man1/getopts.1p.html)
 [![Menul](https://img.shields.io/badge/go-documentation-blue.svg?style=flat-square)](https://www.golinuxcloud.com/bash-getopts/)
 
getopts는 getopt와 비슷한 면이 많지만 다른 기능을 제공합니다. <br>
getopts는 getopt와 마찬가지로 각 명령에 대해 option을 지정 할 수 있습니다. <br>
독립형 프로그램인 getopt에서 명령어 형식으로 사용할 수  쉘 내장 프로그램인 getopts로 발전 했습니다.<br> 
 
# getopts의 역사
1980년 이전에 출시된 getopt는 쉘에 내장되지 않은 독립 실행형프로그램(standalone program)이였다.
**getopts**의 이전 버전이라고 볼 수 있는 **getopt**는 인수의 공백이나 셀 메타 문자를 처리 할 수 없었고, 오류 메시지 출력을 비활성화하는 기능을 제공하지 않았다.<br>
이러한 여러가지 문제점을 해결하기 위해 **getopt**의 문제점을 보완해 Bourn Shell과 Bash Shell에서 모두 사용할 수 있는  <br>
**getopts**가 1986년 유닉스 SVR3과 함께 Born Shell에 처음 소개가 되었다.<br>
 
# 직접 작성한 tmp.sh 예제
```shell
#!/bin/bash
help() {
        echo "split [OPTIONS] argument"
        echo "  -h      도움말 출력."
        echo "  -a ARG  과일 먹기."
        echo "  -b ARG  잠자기."
        exit 0
}

while getopts "a:b:c" opt
do
        case $opt in
                a) arg_a=$OPTARG
                   echo "$arg_a Eating" ;;
                b) arg_b=$OPTARG
                   echo "$arg_b Sleep" ;;
                h) help;;
                ?) help;;
        esac
done

#getopt 부분 끝나고 난 후의 인자(FILE)읽기
shift $(( $OPTIND -1))
file=$1
echo "$file"
```
# 실행 결과1 
![tmp.sh_1](https://user-images.githubusercontent.com/54762273/141643598-e2cd8128-fe50-469c-9a83-b26c872261a5.PNG)

# 실행 결과2
![1](https://user-images.githubusercontent.com/54762273/141643652-e07b136c-af04-4e9d-bedc-c197c4ed92ea.PNG)

# :crescent_moon: sed

[![Menul](https://img.shields.io/badge/go-documentation-blue.svg?style=flat-square)](https://linux.die.net/man/1/sed)
 
**sed(Stream Editor)** 는 **sed** 라는 명령어로 원본 텍스트 파일을 수정하는 유용한 명령어 입니다.<br>
편집기에는 대표적으로 vim, nano등 여러가지 편집기가 있는데 이러한 편집기는 작업이 다 끝나게 되면 <br>원래의 파일을 변경해서 저장을 하게 됩니다. 
하지만 sed는 위의 편집기들과는 차이점이 있습니다 <br>
 
- sed는 명령어 형태로 편집이 되며 vim,nano 에디터들처럼 실시간 편집이 아닙니다.
- 원본을 건드리지 않고 편집하기 때문에 작업이 완료 되어도 원본에는 변화가 없습니다. ( 단 -i 옵션을 주게 되면 원본도 같이 변경)
- 내부적으로 특수한 저장 공간인 버퍼를 사용. (패턴 버퍼와, 홀드 버퍼)
 
 
# 흐름도 :bulb:
<img src = "https://user-images.githubusercontent.com/54762273/141666875-b6174589-310c-4c5f-8aa2-e8b1789acbb2.PNG" width="200" height="400" />

기본적으로 **sed**는 내부적으로 2개의 버퍼를 가지고 있습니다. <br>
위의 그림을 보면 **sed**는 파일의 내용을 **InputStream** 을 통해 그대로 가져옵니다.<br> 
그리고 난 후 패턴 버퍼에 그 내용을 담고 있으며 데이터의 변형과 추가를 위해 다시 임시 버퍼(홀드버퍼)를 사용합니다. <br>
그리고 작업이 완료되면 패턴 버퍼에 그 내용이 담기며 그 내용을 **OutputStream**으로 보내주게 되면 원하는 결과가 출력됩니다. <br>
#### 참고로 OutputStream은 표준 출력 stout입니다.
 
# sed 옵션 :bulb:
|옵션|설명|
|:---:|:---:|
|-n|패턴 버퍼의 내용을 자동 출력하지 않습니다.|
|-e|우리가 사용할 command를 가지고 텍스트 파일을 가공해줍니다.|
|p|특정 행을 출력|
 
# 예제

![1](https://user-images.githubusercontent.com/54762273/141960470-ad58fe42-f90f-4fcc-942c-b18fd8ad4a99.PNG)
 
위의 내용을 가진 텍스트 파일을 가지고 예를 들어 보겠습니다. <br><br>
 
---
**첫번째 줄 출력**
<br>
``
sed -e '1p' ./exe.txt
``

<br>

**실행예시** <br>
![1](https://user-images.githubusercontent.com/54762273/141960724-fc713c7e-3ee9-4323-be4e-d9d1dc5aa5ae.PNG)


이렇게 **-n** 옵션을 주지 않으면 1번째 줄을 출력한 후 **패턴 버퍼의 내용을 자동 출력하게 됩니다.**<br>

---
**패턴버퍼 내용 지우고 첫번째 줄 출력**
<br>
`` sed -n -e '1p' ./ex.txt 
``

<br>
 
**실행예시**
![캡처](https://user-images.githubusercontent.com/54762273/141668059-f98f5c25-99b1-46ec-9631-fcc85284c4eb.PNG)

위 처럼 **-n**옵션을 사용하면 패턴 버퍼의 내용을 출력하지 않고 첫 번째 줄만 출력하게 됩니다.  
**p는 print의 p를 의미합니다.** <br>
 
---
**start, end 출력**
<br>
``
 sed -n -e '1,4p' ./ex.txt
``
 
**실행예시**
![image](https://user-images.githubusercontent.com/54762273/141989968-c488e2ed-50b9-415b-9567-f0d8be483ea2.png)


---
**-e를 활용한 1번째, 3번째 줄**
 <br>
``
  sed -n -e '1p' -e '3p' ./ex.txt
``
 
**실행예시**
![1](https://user-images.githubusercontent.com/54762273/141960996-5497277e-4bb5-4bbd-83cf-f2a895b55244.PNG)
 
# :crescent_moon: awk

[![Menul](https://img.shields.io/badge/go-documentation-blue.svg?style=flat-square)](https://man7.org/linux/man-pages/man1/awk.1p.html)
 
**awk(Aho Weinberger Kernighan)** 는 유닉스에서 **개발된 스크립트 언어로**,<br>
텍스트가 저장되어 있는 파일을 원하는대로 필터링 및 추가를 해서 나온 결과를 열로 출력해주는 프로그램입니다.<br><br>
이와 비슷한 명령어로 **grep**이 있는데 **awk**는 보다 더 일반적으로, <br>
입력 테스트 행에 대해 여러가지 조치를 실행 할 수 있으며 set 명령어와 조합하여 많이 사용하기도 합니다.<br><br>

입력으로부터 한 줄씩을 읽어서 정규 표현식으로 조건이 맞는지를 검사하고 참면 그 줄에 대해 명령어를 실행하는 형식입니다.
 
# 동작원리 :bulb:
* awk는 파일 또는 파이프를 통해 입력 라인을 얻어와 $0라는 내부 변수에 라인을 입력.<br>
* 각 라인은 레코드라고 부르고, newline에 의해 구분 <br>
* 라인은 공백을 기준으로 각각의 필드나 단어로 나뉜다.<br>
* 필드는 $1부터 시작. 많으면 100개 이상의 필드를 저장할 수 있음 <br>
* 내장 변수인 FS라고 부르는 필드 분리자가 공백을 할당받는다. <br>
* 필드가 콜론이나 대시와 같은 문자에 의해 분리되면 새로운 필드 분리자로 FS의 값을 변경할 수 있다. <br>
* awk는 화면에 필드를 출력할 때 print 함수를 사용한다. <br>
* 콤마는 출력필드 분리자(OFS)와 매핑되어 있으며 공백을 할당받음 <br>
 
# 옵션 :bulb:

|옵션|설명|
|:---:|:---:|
|-u|버퍼를 사용하지 않고 출력한다.|
|-F|확장된 정규 표현식으로 필드 구분자를 지정한다, 다중 필드 구분자 사용 가능하다.|
|-f|awk 명령 스크립트를 파일에서 읽어온다.|
|-v|스크립트를 실행하기 전에 미리 변수를 지정하여 준다.|


# 예제
 
**1열 출력**
<br>
 
``
  awk '{print $1 }' ./ex.txt
`` 
 
**실행예시** <br>
![1](https://user-images.githubusercontent.com/54762273/141955439-d1ea9757-2eab-4988-870e-ca432bd6b25d.PNG)
 
---
**1열, 2열 출력**
<br>
 
``
 awk '{print $1,$2}' ./ex.txt
`` 

**실행예시** <br>
![1](https://user-images.githubusercontent.com/54762273/141955951-9e74af09-a97b-4759-b468-b769d2f9f57a.PNG)

 
---
**반복문**
<br>
 
``
awk '{
for(i=0;i<2;i++)
 print( "for loop :" i "\t" $1, $2, $3)
}' ./ex.txt
``
 
**실행예시** <br>
![1](https://user-images.githubusercontent.com/54762273/141957062-707dab52-8c99-4a91-be0a-99ee71c188cd.PNG)

위와 같이 **awk**는 하나의 언어이기 때문에 for문 같은 함수도 사용 할 수 있습니다. <br> <br>
또한 여러가지 정규식도 사용할 수 있습니다.

 




