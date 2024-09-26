const storyData = {
    start: {
        text: "강준호 형사는 늦은 저녁, 낡은 주택가의 한 아파트에서 발생한 살인사건 현장에 도착한다. 피해자는 동네에서 자주 보이던 평범한 회사원이었다. 방 안은 깔끔하게 정돈되어 있었지만, 자세히 살펴보면 미세한 어긋남이 있었다. 강준호는 범인이 계획적으로 범행을 저질렀다고 직감한다.",
        image: "resources/img/start.jpg",
        choices: [
            { text: "방 안의 단서를 철저히 조사한다.", next: "choice1" },
            { text: "피해자의 과거를 조사해 본다.", next: "choice2" },
            { text: "주변 사람들의 알리바이를 확인한다.", next: "choice3" }
        ]
    },
    choice1: {
        text: "강준호는 현장을 자세히 살펴보며 작은 단서들을 찾기 시작한다. 책상 위에는 회사 서류와 가정용품들이 어지럽게 놓여 있었다.",
        image: "resources/img/choice1.jpg",
        choices: [
            { text: "깨진 유리컵을 분석해 본다.", next: "choice1_1" },
            { text: "열린 창문을 통해 범인의 도주 경로를 추적한다.", next: "choice1_2" },
            { text: "방 안에 남겨진 메모를 해독한다.", next: "choice1_3" }
        ]
    },
    choice1_1: {
        text: "강준호는 유리컵을 분석하기 시작한다. 유리 조각에 묻은 미세한 흔적들이 그를 더욱 긴장하게 만든다.",
        image: "resources/img/choice1_1.jpg",
        choices: [
            { text: "유리컵에 남은 지문을 채취하여 분석한다.", next: "choice1_1_1" },
            { text: "컵이 깨진 이유를 추적해 본다.", next: "choice1_1_2" },
            { text: "유리컵 옆에 놓인 서류에 주목한다.", next: "choice1_1_3" }
        ]
    },
    choice1_1_1: {
        text: "유리컵에 남아 있던 지문을 분석하며, 그 지문의 출처를 찾기 시작한다. 지문은 근처에서 일하던 배달부와 일치했다. 강준호는 그 배달부를 만나 대화를 나눈다.",
        image: "resources/img/choice1_1_1.jpg",
        choices: [
            { text: "배달부를 심문하여 지문이 남겨진 경위를 알아낸다.", next: "choice1_1_1_1" },
            { text: "배달부의 근무 기록을 조사하여 행적을 추적한다.", next: "choice1_1_1_2" },
            { text: "배달부의 주변 사람들을 조사하여 알리바이를 확인한다.", next: "choice1_1_1_3" }
        ]
    },
    choice1_1_1_1: {
        text: "강준호는 배달부를 심문한다. 배달부는 그날 짐을 전달하며 잠깐 방에 들어갔다고 말하지만, 불안한 눈빛이 포착된다.",
        image: "resources/img/choice1_1_1_1.jpg",
        choices: [],
        ending: {
            text: "배달부는 결국 자신이 피해자와 말다툼을 하다 순간적으로 화를 참지 못하고 우발적으로 범행을 저질렀다고 고백한다. 배달부는 평소 피해자가 자신을 무시하고 모욕하는 발언을 일삼았다고 주장했다. 사건은 단순한 감정의 폭발에서 비롯된 것으로 결론지어졌고, 강준호는 배달부의 어두운 표정을 마지막으로 사건을 종결지었다.",
            image: "resources/img/choice1_1_1_1_ending.jpg"
        }
    },
    choice1_1_1_2: {
        text: "강준호는 배달부의 근무 기록을 조사하며, 그가 사건 전후에 어디에 있었는지 추적한다. 그의 행적에는 석연치 않은 점들이 있었다.",
        image: "resources/img/choice1_1_1_2.jpg",
        choices: [],
        ending: {
            text: "조사 끝에 배달부는 사건 당일 다른 범죄 조직과 연관되어 있었던 사실이 밝혀졌다. 그는 피해자의 집에 침입한 후 계획적으로 물건을 훔치려 했으나, 예상치 못한 충돌이 발생하면서 살인으로 이어졌다. 강준호는 배달부의 조직과 연루된 범죄를 파헤치며, 더 깊은 음모가 있음을 알게 된다.",
            image: "resources/img/choice1_1_1_2_ending.jpg"
        }
    },
    choice1_1_1_3: {
        text: "강준호는 배달부와 친분이 있는 사람들을 만나며, 배달부가 사건 전후 이상한 행동을 보였다는 이야기를 듣는다.",
        image: "resources/img/choice1_1_1_3.jpg",
        choices: [],
        ending: {
            text: "배달부의 지인들은 그의 평소와는 다른 이상한 행동을 증언했다. 그는 사건 후 자주 술에 취해 다녔으며, 누군가를 피하는 듯했다. 강준호는 배달부의 내면에 숨겨진 죄책감과 두려움을 간파하고, 결국 사건의 진실을 밝히며 배달부를 체포한다.",
            image: "resources/img/choice1_1_1_3_ending.jpg"
        }
    },
    choice1_1_2: {
        text: "강준호는 유리컵이 깨진 이유를 찾기 시작한다. 누군가와의 실랑이 흔적이 컵에 남아 있었다.",
        image: "resources/img/choice1_1_2.jpg",
        choices: [
            { text: "유리 조각에서 혈흔을 찾아 분석한다.", next: "choice1_1_2_1" },
            { text: "유리컵을 깬 사람의 흔적을 추적한다.", next: "choice1_1_2_2" },
            { text: "유리컵이 깨진 상황을 재현해 본다.", next: "choice1_1_2_3" }
        ]
    },
    choice1_1_2_1: {
        text: "강준호는 유리컵 조각에서 미세한 혈흔을 발견하고 이를 분석한다. 혈흔은 피해자의 것이 아니었고, 범인이 다친 흔적이었다.",
        image: "resources/img/choice1_1_2_1.jpg",
        choices: [],
        ending: {
            text: "혈흔 분석 결과, 범인은 피해자의 회사 동료로 밝혀졌다. 두 사람은 사소한 말다툼에서 시작된 다툼이 격해지며 유리컵이 깨졌고, 동료는 우발적으로 피해자를 밀쳐 사망에 이르게 했다. 사건의 진실이 밝혀지자 동료는 깊은 후회와 함께 죄를 인정했다.",
            image: "resources/img/choice1_1_2_1_ending.jpg"
        }
    },
    choice1_1_2_2: {
        text: "강준호는 유리컵을 깬 사람의 흔적을 추적하기 시작한다. 범인은 도주 중 컵을 깨트리며 작은 상처를 입은 것으로 보인다.",
        image: "resources/img/choice1_1_2_2.jpg",
        choices: [],
        ending: {
            text: "추적 끝에 범인의 신원이 드러났다. 그는 피해자의 이웃으로, 평소 피해자와 갈등이 깊었다. 범인은 격렬한 다툼 중 유리컵을 깨며 부상을 입었고, 사건 현장을 서둘러 떠났다. 결국 그는 자신의 실수를 인정하며 자수를 선택했다.",
            image: "resources/img/choice1_1_2_2_ending.jpg"
        }
    },
    choice1_1_2_3: {
        text: "강준호는 유리컵이 깨진 상황을 재현하며, 범인이 실수로 깨뜨렸는지 의도적으로 깼는지 파악하려 한다.",
        image: "resources/img/choice1_1_2_3.jpg",
        choices: [],
        ending: {
            text: "재현을 통해, 유리컵은 의도적으로 깨진 것이 아닌 실수로 떨어진 것임이 밝혀졌다. 범인은 피해자와의 말다툼 도중 흥분해 컵을 떨어뜨렸고, 그 과정에서 발생한 사고가 결국 사건으로 이어졌다. 강준호는 범인이 계획적이지 않았음을 확인하고 사건을 종결지었다.",
            image: "resources/img/choice1_1_2_3_ending.jpg"
        }
    },
    choice1_1_3: {
        text: "강준호는 유리컵 옆에 놓인 서류에 주목한다. 서류는 피해자가 다니던 회사와 관련된 문서였다.",
        image: "resources/img/choice1_1_3.jpg",
        choices: [
            { text: "서류 속 특정 이름을 조사한다.", next: "choice1_1_3_1" },
            { text: "서류의 작성 날짜와 시간에 주목해 그때의 상황을 추적한다.", next: "choice1_1_3_2" },
            { text: "서류 속 암호를 해독해 숨겨진 정보를 파헤친다.", next: "choice1_1_3_3" }
        ]
    },
    choice1_1_3_1: {
        text: "강준호는 서류에 등장하는 인물을 조사한다. 그 인물은 피해자의 동료였으며, 최근 큰 갈등이 있었다는 사실을 알게 된다.",
        image: "resources/img/choice1_1_3_1.jpg",
        choices: [],
        ending: {
            text: "서류 속 인물은 피해자의 직장 동료이자 라이벌이었다. 두 사람의 경쟁은 심화되어 결국 직장 내 불화로 이어졌고, 그 갈등은 사적인 복수로까지 번지게 되었다. 강준호는 이 사건이 단순한 직장 내 갈등에서 비롯된 비극임을 깨달았다.",
            image: "resources/img/choice1_1_3_1_ending.jpg"
        }
    },
    choice1_1_3_2: {
        text: "강준호는 서류의 작성 시점을 분석하며, 피해자가 그 시기에 회사 내에서 어떤 문제를 겪고 있었는지를 파악한다.",
        image: "resources/img/choice1_1_3_2.jpg",
        choices: [],
        ending: {
            text: "서류 분석 결과, 피해자는 회사의 비리를 조사 중이었고, 그로 인해 위협을 받았다. 회사 내부의 음모가 얽혀 있었고, 피해자는 이를 폭로하려 했으나 그 과정에서 공격을 당했다. 사건은 결국 기업 내부의 은폐된 진실이 낳은 비극으로 결론지어졌다.",
            image: "resources/img/choice1_1_3_2_ending.jpg"
        }
    },
    choice1_1_3_3: {
        text: "강준호는 서류 속 암호를 해독하기 시작한다. 암호는 회사의 내부 비밀을 담고 있었다.",
        image: "resources/img/choice1_1_3_3.jpg",
        choices: [],
        ending: {
            text: "암호를 해독한 결과, 피해자는 회사의 중요한 기밀을 알고 있었고, 이를 빌미로 협박을 당하고 있었다. 강준호는 피해자가 내부 고발을 준비 중이었다는 사실을 알게 되었고, 사건의 배경에 숨겨진 복잡한 이해관계를 풀어냈다.",
            image: "resources/img/choice1_1_3_3_ending.jpg"
        }
    },
    choice1_2: {
        text: "강준호는 창문을 조사하며 범인의 도주 경로를 추적한다. 창문 밖에는 범인의 발자국이 남아 있지 않았다.",
        image: "resources/img/choice1_2.jpg",
        choices: [
            { text: "정원에서 숨겨진 단서를 찾는다.", next: "choice1_2_1" },
            { text: "인근 CCTV를 확인해 도주 장면을 분석한다.", next: "choice1_2_2" },
            { text: "창문 근처에서 발견된 물건을 조사한다.", next: "choice1_2_3" }
        ]
    },
    choice1_2_1: {
        text: "강준호는 정원을 꼼꼼히 조사하며, 범인이 미처 숨기지 못한 작은 증거를 찾아낸다. 정원의 흙 위에 미세하게 남은 신발 자국과 작은 조각들이 발견되었다.",
        image: "resources/img/choice1_2_1.jpg",
        choices: [
            { text: "신발 자국을 분석해 범인의 이동 경로를 추적한다.", next: "choice1_2_1_1" },
            { text: "발견된 조각을 조사해 범인의 도구와 연관성을 찾는다.", next: "choice1_2_1_2" },
            { text: "정원 주변을 탐색하며 추가적인 단서를 찾아본다.", next: "choice1_2_1_3" }
        ]
    },
    choice1_2_1_1: {
        text: "강준호는 신발 자국을 분석해 범인이 도주한 경로를 추적한다. 자국은 정원 문을 통해 빠져나가 인적이 드문 골목으로 이어지고 있었다.",
        image: "resources/img/choice1_2_1_1.jpg",
        choices: [],
        ending: {
            text: "추적 결과, 범인은 정원의 작은 문을 통해 빠져나가 어두운 골목으로 도주했다. 범인은 그 골목에서 대기 중이던 차량에 탑승해 흔적을 남기지 않으려 했으나, CCTV와 정원의 단서들이 그의 도주를 명확히 드러냈다. 범인은 결국 자신이 저지른 일을 인정하며 자수했다.",
            image: "resources/img/choice1_2_1_1_ending.jpg"
        }
    },
    choice1_2_1_2: {
        text: "강준호는 정원에서 발견된 조각을 조사한다. 그것은 범인이 사용한 도구의 일부분으로, 이 도구가 사건 당시 중요한 역할을 했음을 암시한다.",
        image: "resources/img/choice1_2_1_2.jpg",
        choices: [],
        ending: {
            text: "조각은 범인이 사건 당시 사용한 도구의 일부였다. 이 도구는 피해자를 협박하거나 강제로 제압하는 데 사용된 것으로 밝혀졌다. 강준호는 도구를 통해 범인의 정체를 파악하고, 사건의 진실을 밝혀내며 범인을 체포한다.",
            image: "resources/img/choice1_2_1_2_ending.jpg"
        }
    },
    choice1_2_1_3: {
        text: "강준호는 정원 주변을 더 자세히 탐색하며 추가적인 단서를 발견한다. 오래된 담배꽁초와 함께 누군가의 손수건이 버려져 있었다.",
        image: "resources/img/choice1_2_1_3.jpg",
        choices: [],
        ending: {
            text: "손수건은 범인이 사건 도중 사용한 것으로, 그 위에 남아 있던 DNA가 결정적인 증거가 되었다. 담배꽁초 역시 범인의 습관을 나타내며 그의 신원을 특정하는 데 큰 역할을 했다. 강준호는 이러한 단서를 토대로 범인을 추적해 사건을 해결한다.",
            image: "resources/img/choice1_2_1_3_ending.jpg"
        }
    },
    choice1_2_2: {
        text: "강준호는 인근 CCTV 영상을 확인하며 범인의 도주 장면을 분석한다. 영상 속 범인은 얼굴을 가리고 있었지만, 독특한 행동을 통해 단서를 발견한다.",
        image: "resources/img/choice1_2_2.jpg",
        choices: [
            { text: "행동 패턴을 분석해 범인의 과거 범죄와의 연관성을 찾는다.", next: "choice1_2_2_1" },
            { text: "CCTV 영상을 복원하여 범인의 더 많은 움직임을 확인한다.", next: "choice1_2_2_2" },
            { text: "영상 속 범인의 동선을 따라가며 추가적인 단서를 찾는다.", next: "choice1_2_2_3" }
        ]
    },
    choice1_2_2_1: {
        text: "강준호는 범인의 특이한 행동 패턴을 분석해, 과거에 유사한 사건을 저질렀던 용의자와의 연결점을 찾아낸다.",
        image: "resources/img/choice1_2_2_1.jpg",
        choices: [],
        ending: {
            text: "행동 패턴 분석을 통해 범인은 과거에도 유사한 수법으로 범행을 저질렀던 전력이 있는 것으로 드러났다. 강준호는 과거의 사건들과 연관된 인물을 추적하며, 이번 사건 역시 같은 범인이 저지른 것임을 밝혀냈다. 결국 범인은 체포되어 과거의 죄와 함께 심판을 받게 되었다.",
            image: "resources/img/choice1_2_2_1_ending.jpg"
        }
    },
    choice1_2_2_2: {
        text: "강준호는 손상된 CCTV 영상을 복원하여 범인의 도주 경로를 더욱 명확히 파악한다.",
        image: "resources/img/choice1_2_2_2.jpg",
        choices: [],
        ending: {
            text: "복원된 영상은 범인이 도주하는 순간을 더욱 선명하게 드러냈다. 범인은 CCTV 사각지대를 철저히 이용했지만, 결정적인 순간을 피하지 못했다. 영상을 토대로 강준호는 범인의 도주 경로를 좁혀 나갔고, 결국 그의 은신처를 찾아내며 사건을 마무리했다.",
            image: "resources/img/choice1_2_2_2_ending.jpg"
        }
    },
    choice1_2_2_3: {
        text: "강준호는 CCTV 속에서 범인이 지나간 장소를 추적하며 현장에서 추가적인 단서를 발견한다.",
        image: "resources/img/choice1_2_2_3.jpg",
        choices: [],
        ending: {
            text: "강준호는 CCTV에 기록된 범인의 이동 경로를 추적하며, 그가 지나간 장소에서 중요한 증거들을 찾아냈다. 범인은 도주 중 실수를 저질렀고, 그로 인해 남긴 단서들이 사건을 해결하는 열쇠가 되었다. 결국 강준호는 범인을 체포하며 사건을 종결지었다.",
            image: "resources/img/choice1_2_2_3_ending.jpg"
        }
    },
    choice1_2_3: {
        text: "강준호는 창문 근처에서 발견된 작은 물건을 조사한다. 그것은 범인이 급히 도망치면서 떨어뜨린 것이었고, 물건에는 특정 문양이 새겨져 있었다.",
        image: "resources/img/choice1_2_3.jpg",
        choices: [
            { text: "문양의 출처를 파악해 조직의 정보를 수집한다.", next: "choice1_2_3_1" },
            { text: "물건을 통해 범인의 신원을 특정한다.", next: "choice1_2_3_2" },
            { text: "조직의 일원을 만나 정보를 얻어낸다.", next: "choice1_2_3_3" }
        ]
    },
    choice1_2_3_1: {
        text: "강준호는 문양이 특정 범죄 조직의 상징임을 알아내고, 조직의 정보를 파헤치기 시작한다.",
        image: "resources/img/choice1_2_3_1.jpg",
        choices: [],
        ending: {
            text: "문양은 특정 범죄 조직의 상징으로 밝혀졌고, 이 조직은 지역 내에서 여러 범죄를 저지르고 있었다. 강준호는 조직과의 연결고리를 파헤쳐 범인이 조직의 일원임을 밝혀냈고, 결국 조직의 핵심 인물을 체포하며 사건을 종결했다.",
            image: "resources/img/choice1_2_3_1_ending.jpg"
        }
    },
    choice1_2_3_2: {
        text: "강준호는 물건에 남아 있던 미세한 지문과 단서를 통해 범인의 신원을 특정해 나간다.",
        image: "resources/img/choice1_2_3_2.jpg",
        choices: [],
        ending: {
            text: "지문 분석 결과, 범인은 조직에 소속된 인물로, 과거에도 여러 범죄에 연루된 전력이 있었다. 물건에 남아 있는 흔적들이 범인의 정체를 밝히는 중요한 단서가 되었고, 강준호는 이를 통해 범인을 추적해 체포했다.",
            image: "resources/img/choice1_2_3_2_ending.jpg"
        }
    },
    choice1_2_3_3: {
        text: "강준호는 조직의 일원을 찾아가 강압적인 심문을 진행하며 사건에 대한 더 많은 정보를 얻어낸다.",
        image: "resources/img/choice1_2_3_3.jpg",
        choices: [],
        ending: {
            text: "조직의 일원과의 대화에서 강준호는 범인이 조직의 지시를 받고 행동했음을 알아냈다. 조직은 피해자를 표적으로 삼았고, 사건은 치밀하게 계획된 것이었다. 강준호는 조직의 배후를 밝혀내며 사건을 마무리했다.",
            image: "resources/img/choice1_2_3_3_ending.jpg"
        }
    },
    choice1_3: {
        text: "강준호는 방 안에 남겨진 작은 메모를 발견한다. 메모에는 '늦은 밤의 진실을 찾아라'는 문장이 적혀 있었다.",
        image: "resources/img/choice1_3.jpg",
        choices: [
            { text: "밤에 현장을 다시 조사한다.", next: "choice1_3_1" },
            { text: "메모의 문장을 분석해 숨겨진 의미를 찾는다.", next: "choice1_3_2" },
            { text: "메모의 필적을 분석해 남긴 사람을 찾는다.", next: "choice1_3_3" }
        ]
    },
    choice1_3_1: {
        text: "강준호는 메모의 힌트를 따라 밤 시간에 현장을 다시 찾는다.",
        image: "resources/img/choice1_3_1.jpg",
        choices: [],
        ending: {
            text: "밤에 현장을 다시 조사한 결과, 사건의 숨겨진 흔적들이 보이기 시작했다. 어둠 속에서 찾은 단서들이 사건의 진실을 밝혀내는 열쇠가 되었고, 강준호는 이내 사건의 전모를 이해하게 되었다. 그는 범인의 실수를 발견하며 사건을 종결지었다.",
            image: "resources/img/choice1_3_1_ending.jpg"
        }
    },
    choice1_3_2: {
        text: "강준호는 메모의 문장을 분석하며 그 의미를 파헤친다.",
        image: "resources/img/choice1_3_2.jpg",
        choices: [],
        ending: {
            text: "메모의 문장은 단순한 메시지가 아닌, 범인이 남긴 경고였다. 피해자는 사건 전에 이미 위협을 받았고, 그 위협은 결국 그의 목숨을 앗아갔다. 강준호는 이 경고의 의미를 파악하고, 사건의 배후에 있는 자를 밝혀내며 사건을 해결했다.",
            image: "resources/img/choice1_3_2_ending.jpg"
        }
    },
    choice1_3_3: {
        text: "강준호는 메모의 필적을 분석하여 남긴 사람을 추적한다.",
        image: "resources/img/choice1_3_3.jpg",
        choices: [],
        ending: {
            text: "메모의 필적 분석을 통해 남긴 사람이 범인임이 밝혀졌다. 그는 피해자에게 사전에 경고를 보냈지만, 결국 행동에 옮기고 말았다. 강준호는 필적을 통해 범인의 정체를 밝히고 그를 체포하며 사건을 마무리했다.",
            image: "resources/img/choice1_3_3_ending.jpg"
        }
    },
    choice2: {
        text: "강준호는 피해자의 과거를 조사하며, 피해자가 직장 내에서 다툼과 스트레스를 많이 받았다는 사실을 알게 된다.",
        image: "resources/img/choice2.jpg",
        choices: [
            { text: "최근 동료들과의 관계를 조사한다.", next: "choice2_1" },
            { text: "피해자가 얽혔던 사내 문제를 파헤친다.", next: "choice2_2" },
            { text: "피해자의 가족과 친구들을 만나 과거를 파악한다.", next: "choice2_3" }
        ]
    },
    choice2_1: {
        text: "강준호는 피해자의 최근 동료들을 조사한다. 그 중 한 명은 피해자와의 다툼 이후 큰 앙심을 품고 있었다.",
        image: "resources/img/choice2_1.jpg",
        choices: [
            { text: "동료를 직접 심문한다.", next: "choice2_1_1" },
            { text: "동료의 알리바이를 확인한다.", next: "choice2_1_2" },
            { text: "동료와 피해자가 나눈 이메일과 메시지를 조사한다.", next: "choice2_1_3" }
        ]
    },
    choice2_1_1: {
        text: "강준호는 동료를 심문하며 사건의 배후를 캐려고 한다. 동료는 협박을 받았다고 주장하며, 사건이 다른 사람과 얽혀 있음을 암시한다.",
        image: "resources/img/choice2_1_1.jpg",
        choices: [],
        ending: {
            text: "동료는 사건 전 협박을 당했고, 그로 인해 피해자와의 갈등이 격화되었다고 진술했다. 강준호는 동료의 주장 속에 숨겨진 진실을 파헤쳐, 협박의 배후가 사건과 연관이 있음을 밝혀냈다. 사건은 단순한 직장 내 갈등을 넘어선 복잡한 음모로 드러났다.",
            image: "resources/img/choice2_1_1_ending.jpg"
        }
    },
    choice2_1_2: {
        text: "강준호는 동료의 알리바이를 철저히 조사한다. 사건 당일 완벽한 알리바이를 제시했지만, 그 속에 숨겨진 허점을 발견한다.",
        image: "resources/img/choice2_1_2.jpg",
        choices: [],
        ending: {
            text: "조사 끝에 동료의 알리바이는 일부 조작된 것으로 드러났다. 그는 사건 당일 피해자와 마주쳤고, 그 만남이 사건의 발단이 되었다. 동료는 끝내 자신의 잘못을 인정하며 자수를 결심했다.",
            image: "resources/img/choice2_1_2_ending.jpg"
        }
    },
    choice2_1_3: {
        text: "강준호는 동료와 피해자가 주고받은 이메일과 메시지를 조사한다. 메시지 속에는 긴장과 갈등이 담겨 있었다.",
        image: "resources/img/choice2_1_3.jpg",
        choices: [],
        ending: {
            text: "이메일과 메시지에서 피해자와 동료 간의 갈등이 격화되었음을 확인할 수 있었다. 동료는 피해자에게 마지막 경고를 보냈고, 그 경고는 현실이 되어버렸다. 강준호는 이 증거들을 바탕으로 사건의 배후를 파헤쳤다.",
            image: "resources/img/choice2_1_3_ending.jpg"
        }
    },
    choice2_2: {
        text: "강준호는 피해자가 얽혔던 사내 문제를 조사한다. 피해자는 회사 내에서 갈등과 여러 문제로 인해 스트레스를 받고 있었다.",
        image: "resources/img/choice2_2.jpg",
        choices: [
            { text: "피해자가 받았던 경고 메시지를 추적한다.", next: "choice2_2_1" },
            { text: "사내 문제와 관련된 인물을 찾아간다.", next: "choice2_2_2" },
            { text: "피해자가 남긴 업무 기록을 조사한다.", next: "choice2_2_3" }
        ]
    },
    choice2_2_1: {
        text: "강준호는 피해자가 받은 경고 메시지를 추적하여 그 출처를 확인한다.",
        image: "resources/img/choice2_2_1.jpg",
        choices: [],
        ending: {
            text: "경고 메시지는 회사 내의 경쟁자가 보낸 것이었다. 그는 피해자가 자신의 비밀을 폭로할 것을 두려워해 위협을 가했다. 사건은 결국 직장 내에서의 갈등과 경쟁이 빚은 비극으로 밝혀졌다.",
            image: "resources/img/choice2_2_1_ending.jpg"
        }
    },
    choice2_2_2: {
        text: "강준호는 사내 문제와 관련된 인물을 찾아가 심문한다.",
        image: "resources/img/choice2_2_2.jpg",
        choices: [],
        ending: {
            text: "심문 끝에 사내 문제와 얽힌 인물은 사건의 열쇠를 쥐고 있었다. 그는 피해자와의 갈등 속에서 극단적인 선택을 했고, 그 선택이 결국 사건으로 이어졌다. 강준호는 사내 갈등이 어떻게 비극으로 변모했는지 명확히 파악했다.",
            image: "resources/img/choice2_2_2_ending.jpg"
        }
    },
    choice2_2_3: {
        text: "강준호는 피해자가 남긴 업무 기록을 조사하며 그가 마지막으로 겪었던 갈등의 내막을 파헤친다.",
        image: "resources/img/choice2_2_3.jpg",
        choices: [],
        ending: {
            text: "업무 기록은 피해자가 회사 내부의 부정을 조사 중이었다는 사실을 드러냈다. 그는 자신의 직장을 지키기 위해 싸웠지만, 그 과정에서 적을 만들었다. 사건은 피해자가 정당성을 지키려다 희생된 비극으로 마무리되었다.",
            image: "resources/img/choice2_2_3_ending.jpg"
        }
    },
    choice2_3: {
        text: "강준호는 피해자의 가족과 친구들을 만나 과거를 조사한다. 피해자의 주변에는 여러 감정의 얽힘과 갈등이 있었다.",
        image: "resources/img/choice2_3.jpg",
        choices: [
            { text: "피해자의 배우자와 마지막 대화를 재현한다.", next: "choice2_3_1" },
            { text: "피해자의 친구가 가진 비밀을 추적한다.", next: "choice2_3_2" },
            { text: "피해자가 남긴 유언장을 조사한다.", next: "choice2_3_3" }
        ]
    },
    choice2_3_1: {
        text: "강준호는 피해자의 배우자와의 마지막 대화를 재현하며, 피해자가 죽기 전 느꼈던 공포와 혼란을 이해한다.",
        image: "resources/img/choice2_3_1.jpg",
        choices: [],
        ending: {
            text: "배우자와의 대화에서 피해자는 두려움에 사로잡혀 있었다. 그는 자신의 안전을 걱정했고, 그것이 현실로 다가왔다. 강준호는 피해자의 마지막 감정들을 이해하며 사건의 전모를 파악하고, 범인을 밝혀냈다.",
            image: "resources/img/choice2_3_1_ending.jpg"
        }
    },
    choice2_3_2: {
        text: "강준호는 피해자의 친구가 알고 있는 비밀을 추적한다.",
        image: "resources/img/choice2_3_2.jpg",
        choices: [],
        ending: {
            text: "친구는 피해자가 평소 고민을 털어놓았던 유일한 사람이었다. 그는 피해자가 직장과 가정에서 겪었던 고통을 알고 있었고, 그 사실이 사건과 직결되어 있었다. 강준호는 친구의 증언을 통해 사건의 퍼즐을 맞추었다.",
            image: "resources/img/choice2_3_2_ending.jpg"
        }
    },
    choice2_3_3: {
        text: "강준호는 피해자가 남긴 유언장을 조사하며, 유언장 속에 담긴 의도를 파악한다.",
        image: "resources/img/choice2_3_3.jpg",
        choices: [],
        ending: {
            text: "유언장은 피해자가 자신의 생명에 대한 불안을 느꼈음을 보여주었다. 그는 누군가에게 쫓기고 있었고, 결국 그것이 그의 죽음을 초래했다. 강준호는 피해자의 마지막 메시지를 통해 사건의 전말을 밝혀냈다.",
            image: "resources/img/choice2_3_3_ending.jpg"
        }
    },
    choice3: {
        text: "강준호는 피해자와 가까운 사람들의 알리바이를 조사하기 시작한다. 피해자의 비서, 동료, 심지어 가족들까지도 모두 용의선상에 오른다.",
        image: "resources/img/choice3.jpg",
        choices: [
            { text: "피해자의 비서의 알리바이를 조사한다.", next: "choice3_1" },
            { text: "피해자의 가족들과의 관계를 파헤친다.", next: "choice3_2" },
            { text: "피해자의 동료들과의 관계를 조사한다.", next: "choice3_3" }
        ]
    },
    choice3_1: {
        text: "강준호는 피해자의 비서가 사건 당일 어디에 있었는지 조사한다. 비서는 사건 전날 밤까지 피해자와 함께 있었다고 증언하지만, 그 후의 행적이 명확하지 않았다.",
        image: "resources/img/choice3_1.jpg",
        choices: [
            { text: "비서의 통화 기록을 확인한다.", next: "choice3_1_1" },
            { text: "비서가 사건 당일 누구와 만났는지 조사한다.", next: "choice3_1_2" },
            { text: "비서의 사무실을 뒤져 추가 단서를 찾는다.", next: "choice3_1_3" }
        ]
    },
    choice3_1_1: {
        text: "강준호는 비서의 통화 기록을 조사해 사건 당일 통화한 사람들을 확인한다.",
        image: "resources/img/choice3_1_1.jpg",
        choices: [],
        ending: {
            text: "통화 기록은 비서가 사건 당일 중요한 정보를 피해자에게 전달했음을 보여주었다. 그는 피해자와의 마지막 통화에서 미심쩍은 말을 남겼고, 그것이 사건의 단서가 되었다. 강준호는 비서의 말을 추적하며 사건의 진실을 밝혀냈다.",
            image: "resources/img/choice3_1_1_ending.jpg"
        }
    },
    choice3_1_2: {
        text: "강준호는 비서가 사건 당일 만났던 사람들을 추적하며, 그 중 한 명이 사건과 깊은 관련이 있음을 알게 된다.",
        image: "resources/img/choice3_1_2.jpg",
        choices: [],
        ending: {
            text: "비서가 만난 사람은 피해자의 적이었다. 그 만남은 단순한 업무적 만남이 아닌, 사건의 중심에 있었던 비밀 회동이었다. 강준호는 그날의 만남이 사건의 열쇠였음을 깨닫고 사건을 해결했다.",
            image: "resources/img/choice3_1_2_ending.jpg"
        }
    },
    choice3_1_3: {
        text: "강준호는 비서의 사무실을 수색하며, 피해자와 비서 사이에 오간 비밀스러운 문서들을 발견한다.",
        image: "resources/img/choice3_1_3.jpg",
        choices: [],
        ending: {
            text: "문서에는 피해자의 마지막 행적과 그가 조사하던 비밀들이 기록되어 있었다. 비서는 그 문서를 통해 피해자가 얼마나 큰 위험에 처해 있었는지 알고 있었고, 그 사실이 사건을 해결하는 데 중요한 단서가 되었다.",
            image: "resources/img/choice3_1_3_ending.jpg"
        }
    },
    choice3_2: {
        text: "강준호는 피해자의 가족들과의 관계를 조사한다. 피해자의 가족은 오랜 기간 동안 피해자와 갈등을 겪어왔고, 특히 경제적인 문제로 인해 큰 불화가 있었다.",
        image: "resources/img/choice3_2.jpg",
        choices: [
            { text: "가족들의 재산 문제를 추적해 본다.", next: "choice3_2_1" },
            { text: "가족의 알리바이를 조사해 본다.", next: "choice3_2_2" },
            { text: "피해자의 가족 회동에 참석해 비밀스러운 대화를 듣는다.", next: "choice3_2_3" }
        ]
    },
    choice3_2_1: {
        text: "강준호는 피해자 가족들의 재산 문제를 조사하며, 그들이 사건과 연관되어 있다는 강력한 동기를 발견한다.",
        image: "resources/img/choice3_2_1.jpg",
        choices: [],
        ending: {
            text: "가족들은 피해자의 재산을 두고 분쟁을 벌이고 있었고, 그 갈등이 결국 사건으로 이어졌다. 경제적 이익을 둘러싼 가족 간의 다툼은 결국 한 사람의 목숨을 앗아가게 만들었다. 강준호는 그 진실을 밝혀내며 사건을 종결지었다.",
            image: "resources/img/choice3_2_1_ending.jpg"
        }
    },
    choice3_2_2: {
        text: "강준호는 피해자의 가족들이 사건 당일 어디에 있었는지 조사한다.",
        image: "resources/img/choice3_2_2.jpg",
        choices: [],
        ending: {
            text: "조사 결과, 가족 중 한 명이 사건 당일 피해자와의 심한 언쟁 끝에 마지막으로 그를 본 것으로 밝혀졌다. 그 날의 다툼이 사건의 발단이 되었고, 가족은 결국 죄책감을 견디지 못하고 진실을 고백했다.",
            image: "resources/img/choice3_2_2_ending.jpg"
        }
    },
    choice3_2_3: {
        text: "강준호는 피해자의 가족들이 모인 자리에서 그들의 대화를 엿듣는다.",
        image: "resources/img/choice3_2_3.jpg",
        choices: [],
        ending: {
            text: "가족 회동에서 밝혀진 비밀스러운 대화는 사건의 전말을 여실히 드러냈다. 가족 간의 분쟁과 갈등은 예상보다 깊었고, 그 불화는 결국 비극적인 결말을 초래했다. 강준호는 그 대화를 통해 사건을 해결하는 데 성공했다.",
            image: "resources/img/choice3_2_3_ending.jpg"
        }
    },
    choice3_3: {
        text: "강준호는 피해자의 동료들과의 관계를 조사한다. 피해자는 직장에서 경쟁 관계에 있었던 동료들과의 갈등이 심했다.",
        image: "resources/img/choice3_3.jpg",
        choices: [
            { text: "동료의 알리바이를 확인한다.", next: "choice3_3_1" },
            { text: "피해자가 마지막으로 다룬 사건에서의 동료와의 충돌을 조사한다.", next: "choice3_3_2" },
            { text: "피해자의 사무실을 조사해 동료들과의 교류 흔적을 찾는다.", next: "choice3_3_3" }
        ]
    },
    choice3_3_1: {
        text: "강준호는 피해자의 동료 중 한 명의 알리바이를 조사하며, 그가 사건 당일 예상치 못한 행동을 했다는 사실을 발견한다.",
        image: "resources/img/choice3_3_1.jpg",
        choices: [],
        ending: {
            text: "조사 끝에 동료의 알리바이는 거짓으로 판명되었다. 그는 사건 당일 피해자와 만났고, 그 만남이 사건의 발단이 되었다. 동료는 결국 자신의 실수를 인정하며 사건의 책임을 받아들였다.",
            image: "resources/img/choice3_3_1_ending.jpg"
        }
    },
    choice3_3_2: {
        text: "강준호는 피해자가 마지막으로 다루던 사건에서 동료와의 갈등을 조사한다.",
        image: "resources/img/choice3_3_2.jpg",
        choices: [],
        ending: {
            text: "피해자가 다루던 사건은 동료와의 충돌로 이어졌고, 그 갈등은 결국 사건의 원인이 되었다. 동료는 피해자가 자신의 커리어에 위협이 된다고 느꼈고, 그 감정이 결국 비극으로 번졌다. 강준호는 그 진실을 밝혀냈다.",
            image: "resources/img/choice3_3_2_ending.jpg"
        }
    },
    choice3_3_3: {
        text: "강준호는 피해자의 사무실을 조사하며, 동료들과 주고받은 문서와 메시지들을 찾아낸다.",
        image: "resources/img/choice3_3_3.jpg",
        choices: [],
        ending: {
            text: "문서와 메시지 속에는 동료와 피해자의 복잡한 관계와 숨겨진 갈등이 담겨 있었다. 두 사람의 관계는 예상보다 깊고 치열했으며, 그 갈등이 사건의 도화선이 되었다. 강준호는 이를 통해 사건의 전말을 밝히며 마무리 지었다.",
            image: "resources/img/choice3_3_3_ending.jpg"
        }
    }
};

// 시작 함수
function startStory() {
    displayStory("start");
}

// 스토리 표시 함수
function displayStory(part) {
    const story = storyData[part];
    const storyContent = document.getElementById("story-content");

    // 이미지 요소 생성 및 스타일 적용
    const storyImage = document.createElement("img");
    storyImage.src = story.image;
    storyImage.alt = "스토리 이미지";
    storyImage.style.width = "100%"; // 가로 크기를 100%로 설정하여 크게 보이도록 설정
    storyImage.style.maxHeight = "500px"; // 최대 높이를 500px로 설정하여 크게 표시
    storyImage.style.objectFit = "contain"; // 이미지 전체가 보이도록 설정
    storyImage.style.objectPosition = "center"; // 중앙에 위치하도록 설정
    storyImage.style.borderRadius = "10px";
    storyImage.style.margin = "0 auto 20px";
    storyImage.style.display = "block";
    storyImage.style.boxShadow = "0 4px 12px rgba(0, 0, 0, 0.5)";

    // 텍스트 요소 생성
    const storyText = document.createElement("p");
    storyText.textContent = story.text;

    // 기존 내용 초기화 및 새 요소 추가
    storyContent.innerHTML = '';
    storyContent.appendChild(storyImage);
    storyContent.appendChild(storyText);

    // 선택지 버튼 추가
    if (story.choices.length > 0) {
        story.choices.forEach(choice => {
            const button = document.createElement("button");
            button.textContent = choice.text;
            button.className = "choice";
            button.onclick = () => displayStory(choice.next);
            storyContent.appendChild(button);
        });
    } else {
        // '다음' 버튼 추가
        const nextButton = document.createElement("button");
        nextButton.textContent = "다음";
        nextButton.className = "next-button";
        nextButton.onclick = () => displayEnding(story);

        // 다음 버튼 스타일링
        nextButton.style.position = "relative";
        nextButton.style.float = "right";
        nextButton.style.padding = "10px 20px";
        nextButton.style.fontSize = "16px";
        nextButton.style.backgroundColor = "#333"; // 어두운 배경색
        nextButton.style.color = "#FFD700"; // 텍스트 색상을 노란색으로 설정
        nextButton.style.border = "none";
        nextButton.style.borderRadius = "5px";
        nextButton.style.cursor = "pointer";
        nextButton.style.boxShadow = "0 2px 8px rgba(0, 0, 0, 0.3)";
        nextButton.style.transition = "background-color 0.3s ease, transform 0.3s ease";

        // 호버 효과 추가
        nextButton.onmouseover = () => {
            nextButton.style.backgroundColor = "#555"; // 밝아지는 효과
            nextButton.style.transform = "scale(1.05)";
        };
        nextButton.onmouseout = () => {
            nextButton.style.backgroundColor = "#333"; // 원래 색상으로 돌아감
            nextButton.style.transform = "scale(1)";
        };

        storyContent.appendChild(nextButton);
    }
}

// 엔딩 표시 함수
function displayEnding(story) {
    const storyContent = document.getElementById("story-content");
    storyContent.innerHTML = ''; // 기존 내용 초기화

    // 엔딩 텍스트 및 이미지 추가
    const endingText = document.createElement("p");
    endingText.className = "ending";
    endingText.textContent = story.ending.text;

    const endingImage = document.createElement("img");
    endingImage.src = story.ending.image;
    endingImage.alt = "엔딩 이미지";
    endingImage.style.width = "100%"; // 엔딩 이미지도 동일하게 크게 설정
    endingImage.style.maxHeight = "500px"; // 최대 높이를 500px로 설정
    endingImage.style.objectFit = "contain";
    endingImage.style.objectPosition = "center";
    endingImage.style.borderRadius = "10px";
    endingImage.style.margin = "0 auto 20px";
    endingImage.style.display = "block";
    endingImage.style.boxShadow = "0 4px 12px rgba(0, 0, 0, 0.5)";

    // '메인으로' 버튼 생성
    const mainButton = document.createElement("button");
    mainButton.textContent = "메인으로";
    mainButton.className = "main-button";
    mainButton.onclick = () => window.location.href = 'main'; // main로 이동

    // 메인으로 버튼 스타일링
    mainButton.style.position = "relative";
    mainButton.style.marginTop = "20px";
    mainButton.style.padding = "10px 20px";
    mainButton.style.fontSize = "16px";
    mainButton.style.backgroundColor = "#444"; // 어두운 배경색
    mainButton.style.color = "#FFD700"; // 노란색 글씨
    mainButton.style.border = "none";
    mainButton.style.borderRadius = "5px";
    mainButton.style.cursor = "pointer";
    mainButton.style.display = "block";
    mainButton.style.marginLeft = "auto";
    mainButton.style.marginRight = "auto";
    mainButton.style.boxShadow = "0 2px 8px rgba(0, 0, 0, 0.3)";
    mainButton.style.transition = "background-color 0.3s ease, transform 0.3s ease";

    // 호버 효과 추가
    mainButton.onmouseover = () => {
        mainButton.style.backgroundColor = "#666"; // 밝아지는 효과
        mainButton.style.transform = "scale(1.05)";
    };
    mainButton.onmouseout = () => {
        mainButton.style.backgroundColor = "#444"; // 원래 색상으로 돌아감
        mainButton.style.transform = "scale(1)";
    };

    storyContent.appendChild(endingText);
    storyContent.appendChild(endingImage);
    storyContent.appendChild(mainButton); // 메인으로 버튼 추가
}

