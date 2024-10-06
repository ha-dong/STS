const storyData = {
    en: {
        start: {
            text: "Detective Kang Junho, a seasoned officer, quickly moves to the scene of a meticulously planned murder case to track down clues. The case occurred late in the evening at an apartment in a quiet and peaceful neighborhood. The victim was a well-respected company employee known for his trustworthiness among colleagues and neighbors. The room was neatly organized, but Detective Kang noticed subtle irregularities. He is confident that these slight traces will provide the key to solving the case and senses that the crime was meticulously planned. The atmosphere surrounding the scene is tense, and Detective Kang is convinced that this incident was not a mere accident. He meticulously analyzes every element at the scene, knowing that each small detail could be the key to solving the case. He plans further investigations to deeply understand the background of the incident and the victim's life.",
            image: "resources/img/start.jpg",
            choices: [
                { text: "Thoroughly investigate the clues in the room.", next: "choice1" },
                { text: "Investigate the victim's past.", next: "choice2" },
                { text: "Check the alibis of people around.", next: "choice3" }
            ]
        },
        choice1: {
            text: "To investigate the scene more thoroughly, Detective Kang Junho begins examining each item in the room meticulously. On the desk, there are scattered company documents and household items, and various photos and memos are attached to the walls. He believes these items could provide clues to the case and strives to leave no detail unnoticed. Particularly, he tries to understand the significance of the people in the photos and the content of the memos, focusing on finding hidden meanings throughout the room. Detective Kang meticulously records the arrangement and condition of the items to understand the victim's lifestyle and habits, aiming to deduce the perpetrator's psychological state and motive based on this information.",
            image: "resources/img/choice1.jpg",
            choices: [
                { text: "Analyze the broken glass cup.", next: "choice1_1" },
                { text: "Trace the escape route through the open window.", next: "choice1_2" },
                { text: "Decrypt the memo left in the room.", next: "choice1_3" }
            ]
        },
        choice1_1: {
            text: "Detective Kang Junho carefully examines the broken glass cup. He discovers subtle traces on the glass shards and unusual scratches on the handle, suggesting a violent encounter and indicating that there was significant conflict behind the scene. Detective Kang considers the possibility that the scratches were caused by the perpetrator's personal emotions towards the victim and plans further investigations based on this clue.",
            image: "resources/img/choice1_1.jpg",
            choices: [
                { text: "Collect fingerprints from the glass cup for analysis.", next: "choice1_1_1" },
                { text: "Investigate the reason why the cup was broken.", next: "choice1_1_2" },
                { text: "Focus on the documents next to the glass cup.", next: "choice1_1_3" }
            ]
        },
        choice1_1_1: {
            text: "Detective Kang Junho carefully collects the fingerprints left on the glass cup and sends them for analysis. The fingerprint analysis reveals that they match a delivery worker who has been working nearby. Although the delivery worker was generally friendly, this case shows signs that there might be hidden secrets within him. Detective Kang begins analyzing the delivery worker's past records and testimonies from people around him to understand his behavior patterns and recent changes.",
            image: "resources/img/choice1_1_1.jpg",
            choices: [
                { text: "Interrogate the delivery worker to understand how his fingerprint ended up on the cup.", next: "choice1_1_1_1" },
                { text: "Investigate the reason why the cup was broken.", next: "choice1_1_1_2" },
                { text: "Pay attention to the documents next to the glass cup.", next: "choice1_1_1_3" }
            ]
        },
        choice1_1_1_1: {
            text: "During the interrogation of the delivery worker, Detective Kang Junho learns that he stopped by the apartment on his way home after his delivery shift. However, there was a sense of unease in his demeanor, indicating that he might be hiding something. The delivery worker showed signs of nervousness, which Detective Kang interprets as an attempt to conceal the truth. Detective Kang detects traces of deceit in his manner of speaking and body language, deciding that a more thorough examination of his statements is necessary.",
            image: "resources/img/choice1_1_1_1.jpg",
            choices: [],
            ending: {
                text: "The delivery worker eventually confesses that he had an argument with the victim and lost his temper, leading to an accidental death. He explained that the victim had been making dismissive and insulting remarks towards him, causing intense emotions that led to the tragic outcome. Detective Kang resolves the case through the delivery worker's confession and the supporting evidence, bringing a tragic end to the victim's story. Through this case, Detective Kang gains a deeper understanding of the complexities of human relationships and the importance of thorough investigation and analysis in solving cases.",
                image: "resources/img/choice1_1_1_1ding.jpg"
            }
        },
        choice1_1_1_2: {
            text: "Investigate the reason why the cup was broken.",
            image: "resources/img/choice1_1_1_2.jpg",
            choices: [],
            ending: {
                text: "Investigating why the cup was broken, Detective Kang Junho discovers that the glass was shattered due to a sudden scuffle between the victim and the perpetrator. This suggests that the confrontation escalated unexpectedly, leading to the tragic outcome. Detective Kang uses this information to trace the sequence of events, ultimately linking the perpetrator to the scene and solving the case through a combination of physical evidence and witness testimonies.",
                image: "resources/img/choice1_1_1_2ding.jpg"
            }
        },
        choice1_1_1_3: {
            text: "Focus on the documents next to the glass cup.",
            image: "resources/img/choice1_1_1_3.jpg",
            choices: [],
            ending: {
                text: "By focusing on the documents next to the glass cup, Detective Kang Junho finds important papers related to the victim's work. These documents contain sensitive information about an internal project, indicating that the victim was involved in a significant investigation that may have led to the conflict with the perpetrator. Detective Kang uses this evidence to delve deeper into the victim's professional life, uncovering motives related to workplace rivalries and hidden agendas.",
                image: "resources/img/choice1_1_1_3ding.jpg"
            }
        },
        choice1_1_2: {
            text: "Detective Kang Junho begins investigating why the glass cup was broken. He discovers traces of struggle on the shards and concludes that the cup was likely shattered during a heated confrontation between the victim and the perpetrator. This revelation suggests that the altercation was more intense than initially presumed, pointing towards a deeper personal conflict.",
            image: "resources/img/choice1_1_2.jpg",
            choices: [
                { text: "Analyze bloodstains from the glass shards.", next: "choice1_1_2_1" },
                { text: "Trace the person who shattered the glass cup.", next: "choice1_1_2_2" },
                { text: "Reenact the glass-shattering incident.", next: "choice1_1_2_3" }
            ]
        },
        choice1_1_2_1: {
            text: "Detective Kang Junho analyzes the bloodstains found on the glass shards. The analysis reveals that the blood matches the victim, confirming that the glass was indeed shattered during the struggle. This evidence solidifies the theory that the confrontation led to the victim's accidental death.",
            image: "resources/img/choice1_1_2_1.jpg",
            choices: [],
            ending: {
                text: "Bloodstain analysis confirms that the glass was shattered during a violent struggle between the victim and the perpetrator. Detective Kang Junho uses this evidence to establish the sequence of events that led to the victim's death, concluding that it was an accidental outcome of a heated argument. With this information, Detective Kang is able to piece together the timeline and confirm the perpetrator's involvement, ultimately solving the case.",
                image: "resources/img/choice1_1_2_1ding.jpg"
            }
        },
        choice1_1_2_2: {
            text: "Detective Kang Junho traces the person who shattered the glass cup. Through surveillance footage and witness statements, he identifies the perpetrator as a co-worker who had recently had a falling out with the victim. Further investigation into the co-worker's background reveals a motive rooted in jealousy and professional rivalry.",
            image: "resources/img/choice1_1_2_2.jpg",
            choices: [],
            ending: {
                text: "Tracing the perpetrator leads Detective Kang Junho to a disgruntled co-worker who had a history of conflict with the victim. The investigation uncovers that the co-worker's jealousy over the victim's success in the workplace was the primary motive. With sufficient evidence, Detective Kang confronts the co-worker, who eventually admits to the confrontation that unintentionally led to the victim's death. The case is solved as the perpetrator takes responsibility for the tragic incident.",
                image: "resources/img/choice1_1_2_2ding.jpg"
            }
        },
        choice1_1_2_3: {
            text: "Detective Kang Junho reenacts the glass-shattering incident to better understand how the confrontation escalated. Through meticulous analysis and simulations, he determines that the perpetrator acted out of a momentary lapse of control, leading to the accidental death of the victim.",
            image: "resources/img/choice1_1_2_3.jpg",
            choices: [],
            ending: {
                text: "Reenacting the incident allows Detective Kang Junho to visualize the events that transpired. The simulations confirm that the perpetrator, in a fit of rage, unintentionally caused the victim's death by shattering the glass. This reenactment provides a clear understanding of the sequence of actions, helping Detective Kang to piece together the accidental nature of the death and resolve the case accordingly.",
                image: "resources/img/choice1_1_2_3ding.jpg"
            }
        },
        choice1_1_3: {
            text: "Detective Kang Junho focuses on the documents next to the glass cup. Upon examination, he finds that these documents are related to the victim's work and contain sensitive information about a company project. This discovery suggests that the victim was involved in a significant investigation, which might have led to the confrontation with the perpetrator.",
            image: "resources/img/choice1_1_3.jpg",
            choices: [
                { text: "Investigate the specific names mentioned in the documents.", next: "choice1_1_3_1" },
                { text: "Analyze the dates and times the documents were written to trace the situation.", next: "choice1_1_3_2" },
                { text: "Decrypt the codes in the documents to uncover hidden information.", next: "choice1_1_3_3" }
            ]
        },
        choice1_1_3_1: {
            text: "Detective Kang Junho investigates the specific names mentioned in the documents. He discovers that these names belong to key figures within the company who were directly involved in the controversial project. Further investigation reveals strained relationships and professional rivalries among these individuals, providing potential motives for the murder.",
            image: "resources/img/choice1_1_3_1.jpg",
            choices: [],
            ending: {
                text: "The investigation into the names reveals internal conflicts and rivalries within the company. It becomes evident that the victim was at the center of a contentious project that some colleagues wanted to undermine. Detective Kang Junho identifies a primary suspect among these colleagues, whose motive aligns with the evidence gathered. The suspect is confronted and confesses to the murder, driven by professional jealousy and the desire to protect the project's integrity.",
                image: "resources/img/choice1_1_3_1ding.jpg"
            }
        },
        choice1_1_3_2: {
            text: "Detective Kang Junho analyzes the dates and times the documents were written to trace the situation. This analysis helps him understand the timeline of events leading up to the murder, providing insights into the victim's activities and interactions with others during that period.",
            image: "resources/img/choice1_1_3_2.jpg",
            choices: [],
            ending: {
                text: "By analyzing the timestamps on the documents, Detective Kang Junho reconstructs the victim's schedule and identifies key interactions with colleagues. This timeline reveals that the victim had meetings with several individuals who might have had motives for the murder. Detective Kang uses this information to narrow down the list of suspects and focus his investigation on those who had the opportunity and motive to commit the crime.",
                image: "resources/img/choice1_1_3_2ding.jpg"
            }
        },
        choice1_1_3_3: {
            text: "Detective Kang Junho attempts to decrypt the codes found in the documents. Through careful analysis, he uncovers hidden messages that reveal internal communications about the controversial project, shedding light on the underlying tensions and conflicts that may have led to the murder.",
            image: "resources/img/choice1_1_3_3.jpg",
            choices: [],
            ending: {
                text: "Decrypting the codes unveils secret communications between key figures involved in the project. These messages expose plans to sabotage the project and eliminate obstacles, including the victim who was pushing for transparency. Detective Kang Junho uses this decrypted information to build a strong case against the perpetrators, leading to their arrest and the resolution of the case.",
                image: "resources/img/choice1_1_3_3ding.jpg"
            }
        },
        choice1_2: {
            text: "Detective Kang Junho begins tracing the escape route through the open window. He meticulously examines the surroundings to identify any signs of forced entry or exit. By analyzing footprints and other minor traces left behind, he aims to reconstruct the path the perpetrator took to flee the scene.",
            image: "resources/img/choice1_2.jpg",
            choices: [
                { text: "Search the garden for hidden clues.", next: "choice1_2_1" },
                { text: "Check nearby CCTV to analyze the escape scene.", next: "choice1_2_2" },
                { text: "Investigate the items found near the window.", next: "choice1_2_3" }
            ]
        },
        choice1_2_1: {
            text: "Detective Kang Junho thoroughly searches the garden and discovers small traces left by the perpetrator. He finds partial shoe prints and scattered pieces of evidence that indicate the direction the perpetrator took while escaping. These clues are critical in narrowing down the escape route and identifying potential hiding spots.",
            image: "resources/img/choice1_2_1.jpg",
            choices: [
                { text: "Analyze the shoe prints to trace the perpetrator's path.", next: "choice1_2_1_1" },
                { text: "Investigate the broken pieces to find connections to the perpetrator's tools.", next: "choice1_2_1_2" },
                { text: "Continue searching around the garden for more clues.", next: "choice1_2_1_3" }
            ]
        },
        choice1_2_1_1: {
            text: "Detective Kang Junho analyzes the shoe prints found in the garden. The analysis reveals that the perpetrator was wearing a specific type of shoe, which helps narrow down the list of suspects. By comparing these prints with known records, Detective Kang identifies potential individuals who match the footprint pattern.",
            image: "resources/img/choice1_2_1_1.jpg",
            choices: [],
            ending: {
                text: "The shoe print analysis leads Detective Kang Junho to a suspect who owns a pair of similar shoes. Further investigation into this individual's activities around the time of the murder confirms their presence at the scene. With the footprint evidence linking them to the crime, Detective Kang apprehends the suspect, thereby solving the case.",
                image: "resources/img/choice1_2_1_1ding.jpg"
            }
        },
        choice1_2_1_2: {
            text: "Investigating the broken pieces near the window, Detective Kang Junho discovers fragments of a tool that might have been used during the escape. These fragments are matched to tools found in the suspect's possession, providing a direct link between the perpetrator and the escape route.",
            image: "resources/img/choice1_2_1_2.jpg",
            choices: [],
            ending: {
                text: "The broken tool pieces are traced back to the suspect, who possesses identical tools used during the escape. This evidence solidifies the connection between the suspect and the crime scene. Detective Kang Junho uses this link to build a strong case, leading to the suspect's arrest and the resolution of the murder case.",
                image: "resources/img/choice1_2_1_2ding.jpg"
            }
        },
        choice1_2_1_3: {
            text: "Continuing the search around the garden, Detective Kang Junho finds additional clues that further narrow down the escape route. These include discarded items and personal belongings that were left behind, providing more context to the perpetrator's identity and motive.",
            image: "resources/img/choice1_2_1_3.jpg",
            choices: [],
            ending: {
                text: "The additional clues found in the garden help Detective Kang Junho piece together the perpetrator's identity. By analyzing these items, he uncovers the motive behind the murder and establishes a clear connection between the suspect and the victim. This comprehensive evidence leads to the suspect's conviction and the successful closure of the case.",
                image: "resources/img/choice1_2_1_3ding.jpg"
            }
        },
        choice1_2_2: {
            text: "Checking nearby CCTV footage, Detective Kang Junho reviews the escape scene to gather more details about the perpetrator's movements. The footage reveals the direction the perpetrator took after leaving through the open window, helping to pinpoint their next steps.",
            image: "resources/img/choice1_2_2.jpg",
            choices: [
                { text: "Analyze the movement patterns in the CCTV footage.", next: "choice1_2_2_1" },
                { text: "Enhance the CCTV footage to get a clearer view of the perpetrator.", next: "choice1_2_2_2" },
                { text: "Follow the trail shown in the CCTV to locate the perpetrator.", next: "choice1_2_2_3" }
            ]
        },
        choice1_2_2_1: {
            text: "Analyzing the movement patterns in the CCTV footage, Detective Kang Junho identifies unique behavioral traits of the perpetrator. These traits include specific gestures and walking speed, which help in recognizing the individual in future sightings or footage.",
            image: "resources/img/choice1_2_2_1.jpg",
            choices: [],
            ending: {
                text: "The unique movement patterns allow Detective Kang Junho to recognize the perpetrator in subsequent CCTV footage from different locations. This consistent identification across multiple videos leads to the narrowing down of suspects, ultimately resulting in the perpetrator's identification and arrest.",
                image: "resources/img/choice1_2_2_1ding.jpg"
            }
        },
        choice1_2_2_2: {
            text: "Enhancing the CCTV footage, Detective Kang Junho obtains a clearer view of the perpetrator's face. This improved clarity helps in accurately identifying the individual and provides vital information that can be used to locate and apprehend the suspect.",
            image: "resources/img/choice1_2_2_2.jpg",
            choices: [],
            ending: {
                text: "With the enhanced CCTV footage, Detective Kang Junho is able to identify the perpetrator's face, leading to a quick identification through facial recognition systems. This direct identification facilitates the immediate apprehension of the suspect, thereby swiftly resolving the case.",
                image: "resources/img/choice1_2_2_2ding.jpg"
            }
        },
        choice1_2_2_3: {
            text: "Following the trail shown in the CCTV footage, Detective Kang Junho leads the investigation to a nearby abandoned building. Inside, he discovers more evidence linking the perpetrator to the crime, including personal belongings and further incriminating items.",
            image: "resources/img/choice1_2_2_3.jpg",
            choices: [],
            ending: {
                text: "The trail leads Detective Kang Junho to an abandoned building where he finds substantial evidence tying the perpetrator to the murder. The discovery includes personal items that belong to the suspect and additional tools used in the crime. With this evidence, Detective Kang successfully apprehends the perpetrator, ensuring justice for the victim.",
                image: "resources/img/choice1_2_2_3ding.jpg"
            }
        },
        choice1_2_3: {
            text: "Investigating the items found near the window, Detective Kang Junho discovers personal belongings of the perpetrator, including a wallet and a mobile phone. These items provide critical information about the suspect's identity and recent activities, aiding in the swift resolution of the case.",
            image: "resources/img/choice1_2_3.jpg",
            choices: [
                { text: "Identify the owner of the mobile phone.", next: "choice1_2_3_1" },
                { text: "Trace the wallet's contents to gather more information.", next: "choice1_2_3_2" },
                { text: "Analyze the items for any hidden evidence.", next: "choice1_2_3_3" }
            ]
        },
        choice1_2_3_1: {
            text: "Identifying the owner of the mobile phone, Detective Kang Junho discovers that it belongs to a known associate of the victim who had been in frequent contact with him. This association provides a direct link between the victim and the perpetrator, leading to further insights into the motive behind the murder.",
            image: "resources/img/choice1_2_3_1.jpg",
            choices: [],
            ending: {
                text: "The mobile phone reveals communications between the victim and the perpetrator, highlighting a strained relationship and potential motives for the murder. Detective Kang Junho uses this evidence to confront the suspect, who then confesses to the crime, thereby solving the case.",
                image: "resources/img/choice1_2_3_1ding.jpg"
            }
        },
        choice1_2_3_2: {
            text: "Tracing the wallet's contents, Detective Kang Junho finds identification documents and recent transaction records that point to the perpetrator's financial troubles. These troubles provide a plausible motive for the murder, as the perpetrator may have felt compelled to commit the crime to resolve personal financial issues.",
            image: "resources/img/choice1_2_3_2.jpg",
            choices: [],
            ending: {
                text: "The financial records indicate that the perpetrator was under significant financial strain, which may have driven him to murder the victim. Detective Kang Junho uses this motive to build a case against the suspect, leading to his arrest and the successful resolution of the case.",
                image: "resources/img/choice1_2_3_2ding.jpg"
            }
        },
        choice1_2_3_3: {
            text: "Analyzing the items for any hidden evidence, Detective Kang Junho discovers a concealed weapon and a note that further implicates the perpetrator in the murder. This hidden evidence provides concrete proof of the perpetrator's involvement, solidifying the case against him.",
            image: "resources/img/choice1_2_3_3.jpg",
            choices: [],
            ending: {
                text: "The concealed weapon and incriminating note are irrefutable evidence that directly links the perpetrator to the crime. With this proof, Detective Kang Junho is able to arrest the suspect swiftly, ensuring that justice is served and the case is closed.",
                image: "resources/img/choice1_2_3_3ding.jpg"
            }
        },
        choice1_3: {
            text: "Detective Kang Junho decides to decrypt the memo left in the room. The memo contains a series of codes and abbreviations that seem to reference internal company matters. By carefully analyzing and decoding these messages, Detective Kang hopes to uncover hidden information that could shed light on the motive and circumstances surrounding the murder.",
            image: "resources/img/choice1_3.jpg",
            choices: [
                { text: "Use cryptographic tools to decode the memo.", next: "choice1_3_1" },
                { text: "Consult with a cryptography expert for assistance.", next: "choice1_3_2" },
                { text: "Search for patterns in the memo to understand its meaning.", next: "choice1_3_3" }
            ]
        },
        choice1_3_1: {
            text: "Using cryptographic tools, Detective Kang Junho successfully deciphers the memo. The decoded message reveals confidential information about a company project that the victim was involved in, indicating that the victim was on the verge of exposing certain unethical practices within the company. This revelation suggests a possible motive for the murder, as someone may have wanted to prevent the victim from uncovering these secrets.",
            image: "resources/img/choice1_3_1.jpg",
            choices: [],
            ending: {
                text: "The decrypted memo provides a clear motive for the murder, pointing to a cover-up of unethical practices within the company. Detective Kang Junho uses this information to identify key individuals who had the most to lose from the victim's actions. With this motive established, Detective Kang is able to connect the dots and identify the perpetrator, leading to their arrest and the resolution of the case.",
                image: "resources/img/choice1_3_1ding.jpg"
            }
        },
        choice1_3_2: {
            text: "Consulting with a cryptography expert, Detective Kang Junho gains valuable insights into the structure and purpose of the coded memo. The expert helps decode the message, revealing that the victim was tracking financial discrepancies within the company, which may have led to the motive for his murder.",
            image: "resources/img/choice1_3_2.jpg",
            choices: [],
            ending: {
                text: "With the help of the cryptography expert, the memo is fully decoded, uncovering the victim's investigation into financial irregularities. This evidence points directly to someone within the company who had a vested interest in silencing the victim. Detective Kang Junho uses this information to pinpoint the suspect and gathers further evidence to support the case, ultimately leading to the perpetrator's arrest.",
                image: "resources/img/choice1_3_2ding.jpg"
            }
        },
        choice1_3_3: {
            text: "Detective Kang Junho searches for patterns in the memo, identifying recurring symbols and sequences that hint at hidden meanings. Through pattern recognition, he uncovers a reference to a secret meeting that the victim attended the night before the murder, providing a new lead to follow.",
            image: "resources/img/choice1_3_3.jpg",
            choices: [],
            ending: {
                text: "The identified patterns lead Detective Kang Junho to discover the details of a secret meeting that the victim attended. Investigating this meeting reveals that the victim was meeting with influential individuals who had the power to affect the company's operations. This discovery uncovers a network of individuals who may have been involved in the murder to protect their interests. With this new information, Detective Kang is able to connect the suspect to the crime scene and secure a conviction.",
                image: "resources/img/choice1_3_3ding.jpg"
            }
        },
        choice2: {
            text: "Detective Kang Junho decides to investigate the victim's past to uncover any potential motives or hidden aspects of his life that could be related to the murder. He begins by reviewing the victim's personal and professional history, seeking any connections that might provide clues to the case.",
            image: "resources/img/choice2.jpg",
            choices: [
                { text: "Examine the victim's recent workplace conflicts.", next: "choice2_1" },
                { text: "Investigate the victim's personal relationships.", next: "choice2_2" },
                { text: "Look into the victim's financial records.", next: "choice2_3" }
            ]
        },
        choice2_1: {
            text: "Examining the victim's recent workplace conflicts, Detective Kang Junho discovers that the victim was involved in several disputes with his co-workers over project management and credit for work done. These conflicts suggest that the victim had enemies at work who might have had reasons to harm him.",
            image: "resources/img/choice2_1.jpg",
            choices: [
                { text: "Interview the co-workers involved in the disputes.", next: "choice2_1_1" },
                { text: "Review emails and communications related to the conflicts.", next: "choice2_1_2" },
                { text: "Analyze the impact of the conflicts on the victim's behavior.", next: "choice2_1_3" }
            ]
        },
        choice2_1_1: {
            text: "Interviewing the co-workers involved in the disputes, Detective Kang Junho uncovers that one of them had a particularly strong grudge against the victim. This co-worker felt overshadowed by the victim's achievements and was vocal about their resentment.",
            image: "resources/img/choice2_1_1.jpg",
            choices: [],
            ending: {
                text: "The interviews reveal that the disgruntled co-worker had ample motive to commit the murder. Their strong feelings of resentment and the desire to eliminate competition provide a clear motive. Detective Kang Junho gathers further evidence, including witness statements and additional proof, leading to the arrest and confession of the co-worker, thereby solving the case.",
                image: "resources/img/choice2_1_1ding.jpg"
            }
        },
        choice2_1_2: {
            text: "Reviewing the victim's emails and communications related to the conflicts, Detective Kang Junho finds threatening messages directed at the victim from one of his co-workers. These messages indicate a high level of animosity and potential intent to harm.",
            image: "resources/img/choice2_1_2.jpg",
            choices: [],
            ending: {
                text: "The threatening emails serve as concrete evidence of the co-worker's intent to harm the victim. Detective Kang Junho uses these communications to build a strong case against the co-worker, who eventually admits to the murder under the weight of the evidence, leading to the resolution of the case.",
                image: "resources/img/choice2_1_2ding.jpg"
            }
        },
        choice2_1_3: {
            text: "Analyzing the impact of the conflicts on the victim's behavior, Detective Kang Junho observes that the victim had become increasingly stressed and withdrawn in the weeks leading up to the murder. This change in behavior suggests that the victim was under significant pressure, possibly contributing to the motive for his murder.",
            image: "resources/img/choice2_1_3.jpg",
            choices: [],
            ending: {
                text: "The analysis of the victim's stressed and withdrawn behavior indicates that he was on the verge of uncovering or resolving the workplace conflicts, making him a target for those who opposed his actions. Detective Kang Junho uses this insight to focus his investigation on individuals who had the most to gain from the victim's removal, leading to the identification and arrest of the perpetrator.",
                image: "resources/img/choice2_1_3ding.jpg"
            }
        },
        choice2_2: {
            text: "Investigating the victim's personal relationships, Detective Kang Junho learns that the victim had strained relationships with certain family members and friends. These strained relationships could hold keys to understanding the motive behind the murder.",
            image: "resources/img/choice2_2.jpg",
            choices: [
                { text: "Reconstruct the victim's last interactions with his family.", next: "choice2_2_1" },
                { text: "Interview close friends to uncover any hidden conflicts.", next: "choice2_2_2" },
                { text: "Examine the victim's social media for signs of distress.", next: "choice2_2_3" }
            ]
        },
        choice2_2_1: {
            text: "Reconstructing the victim's last interactions with his family, Detective Kang Junho discovers that the victim had recently had a heated argument with his brother over inheritance issues. This argument escalated over time, indicating deep-seated resentment that could have led to the murder.",
            image: "resources/img/choice2_2_1.jpg",
            choices: [],
            ending: {
                text: "The argument over inheritance provides a clear motive for the brother to commit the murder. Detective Kang Junho gathers additional evidence, including testimonies from family members and financial records, leading to the brother's arrest and confession, thereby solving the case.",
                image: "resources/img/choice2_2_1ding.jpg"
            }
        },
        choice2_2_2: {
            text: "Interviewing close friends, Detective Kang Junho uncovers that the victim had recently ended several friendships under suspicious circumstances. These terminations could have created enemies who might harbor motives for the murder.",
            image: "resources/img/choice2_2_2.jpg",
            choices: [],
            ending: {
                text: "The termination of friendships reveals underlying conflicts and potential motives for the murder. Detective Kang Junho identifies a particular friend who had expressed strong negative feelings towards the victim before the murder. With sufficient evidence, this friend is apprehended and confesses to the crime, resolving the case.",
                image: "resources/img/choice2_2_2ding.jpg"
            }
        },
        choice2_2_3: {
            text: "Examining the victim's social media, Detective Kang Junho finds numerous posts expressing distress and hints of ongoing conflicts. These posts provide further evidence of the victim's troubled state, suggesting that he was targeted due to these personal issues.",
            image: "resources/img/choice2_2_3.jpg",
            choices: [],
            ending: {
                text: "The social media posts paint a picture of a man under significant personal stress, which could have made him vulnerable to targeted attacks. Detective Kang Junho uses this information to focus on individuals who were close to the victim and could have exploited his vulnerable state. This leads to the identification and arrest of the perpetrator responsible for the murder.",
                image: "resources/img/choice2_2_3ding.jpg"
            }
        },
        choice2_3: {
            text: "Looking into the victim's financial records, Detective Kang Junho discovers irregularities and large, unexplained transactions. These financial discrepancies could indicate motives related to money, such as embezzlement, fraud, or financial desperation, which might have led to the murder.",
            image: "resources/img/choice2_3.jpg",
            choices: [
                { text: "Audit the victim's financial transactions for suspicious activity.", next: "choice2_3_1" },
                { text: "Trace the sources of the large, unexplained transactions.", next: "choice2_3_2" },
                { text: "Interview financial associates to understand the discrepancies.", next: "choice2_3_3" }
            ]
        },
        choice2_3_1: {
            text: "Auditing the victim's financial transactions, Detective Kang Junho identifies several suspicious payments made to unknown entities. These transactions suggest possible involvement in fraudulent activities or illicit dealings, providing a potential motive for someone to eliminate the victim to cover up these activities.",
            image: "resources/img/choice2_3_1.jpg",
            choices: [],
            ending: {
                text: "The audit uncovers a network of fraudulent transactions linked to the victim. Detective Kang Junho connects these financial irregularities to a co-worker who had the most to gain from the victim's removal. With this evidence, the co-worker is arrested and confesses to the murder to protect their involvement in the fraudulent activities, thereby solving the case.",
                image: "resources/img/choice2_3_1ding.jpg"
            }
        },
        choice2_3_2: {
            text: "Tracing the sources of the large, unexplained transactions, Detective Kang Junho discovers that funds were being siphoned to offshore accounts. This revelation points to a more extensive financial fraud operation, potentially implicating high-level individuals within the company who wanted to protect their illicit gains.",
            image: "resources/img/choice2_3_2.jpg",
            choices: [],
            ending: {
                text: "The discovery of offshore transactions implicates senior executives within the company who were involved in the financial fraud. Detective Kang Junho gathers evidence linking these executives to the murder, as eliminating the victim was necessary to prevent the exposure of their fraudulent activities. The executives are arrested and the case is successfully closed, ensuring that justice is served.",
                image: "resources/img/choice2_3_2ding.jpg"
            }
        },
        choice2_3_3: {
            text: "Interviewing financial associates, Detective Kang Junho uncovers that the victim was pressured into participating in high-risk investments that were losing money. This financial pressure could have driven someone to murder the victim to prevent further losses or to silence him from revealing the fraudulent schemes.",
            image: "resources/img/choice2_3_3.jpg",
            choices: [],
            ending: {
                text: "The interviews reveal that the victim was coerced into engaging in dubious financial activities by a manipulative associate. This associate had strong motives to eliminate the victim to protect their financial interests and prevent exposure of their schemes. Detective Kang Junho uses this information to identify and arrest the associate, thereby resolving the case.",
                image: "resources/img/choice2_3_3ding.jpg"
            }
        },
        choice3: {
            text: "Detective Kang Junho begins investigating the alibis of people close to the victim, including the victim's secretary, family members, and colleagues. Each individual presents their own story, but Detective Kang is determined to find inconsistencies and uncover the truth behind their alibis to identify the real perpetrator.",
            image: "resources/img/choice3.jpg",
            choices: [
                { text: "Investigate the secretary's alibi.", next: "choice3_1" },
                { text: "Dig into the relationship with the victim's family.", next: "choice3_2" },
                { text: "Examine the relationships with the victim's colleagues.", next: "choice3_3" }
            ]
        },
        choice3_1: {
            text: "Investigating the secretary's alibi, Detective Kang Junho discovers that the secretary was with the victim until the night before the murder but lacks a clear timeline of his whereabouts after that. Despite the seemingly perfect alibi, Detective Kang suspects hidden flaws and begins analyzing the secretary's schedule and call logs to uncover the truth.",
            image: "resources/img/choice3_1.jpg",
            choices: [
                { text: "Examine the secretary's call logs.", next: "choice3_1_1" },
                { text: "Investigate who the secretary met on the day of the murder.", next: "choice3_1_2" },
                { text: "Search the secretary's office for additional clues.", next: "choice3_1_3" }
            ]
        },
        choice3_1_1: {
            text: "Examining the secretary's call logs, Detective Kang Junho finds multiple calls made on the day of the murder. Notably, there is a call made to an unknown number shortly before the murder, suggesting a possible interaction that was not accounted for in the alibi.",
            image: "resources/img/choice3_1_1.jpg",
            choices: [],
            ending: {
                text: "The call logs reveal a suspicious call to an unknown number that was not mentioned during the initial alibi. Detective Kang Junho uses this discrepancy to confront the secretary, who eventually admits to the call and reveals that he was coerced into assisting the perpetrator. This admission links the secretary directly to the crime, leading to his arrest and confession.",
                image: "resources/img/choice3_1_1ding.jpg"
            }
        },
        choice3_1_2: {
            text: "Investigating who the secretary met on the day of the murder, Detective Kang Junho discovers that the secretary had a meeting with a known associate of the perpetrator. This meeting raises suspicions about the secretary's involvement in the murder.",
            image: "resources/img/choice3_1_2.jpg",
            choices: [],
            ending: {
                text: "The investigation reveals that the secretary's meeting with the perpetrator's associate was not previously disclosed. This connection provides strong evidence of the secretary's involvement in the murder plot. Detective Kang Junho gathers additional proof, including witness statements and communication records, leading to the secretary's arrest and confession.",
                image: "resources/img/choice3_1_2ding.jpg"
            }
        },
        choice3_1_3: {
            text: "Searching the secretary's office for additional clues, Detective Kang Junho finds a hidden folder containing documents that link the secretary to the financial irregularities within the company. These documents suggest that the secretary was involved in the fraudulent activities that led to the victim's murder.",
            image: "resources/img/choice3_1_3.jpg",
            choices: [],
            ending: {
                text: "The hidden documents provide irrefutable evidence of the secretary's involvement in the company's financial fraud. Detective Kang Junho uses this evidence to confront the secretary, who confesses to orchestrating the murder to protect the fraudulent schemes. This confession, along with the supporting evidence, ensures the case is successfully closed.",
                image: "resources/img/choice3_1_3ding.jpg"
            }
        },
        choice3_2: {
            text: "Digging into the relationship with the victim's family, Detective Kang Junho uncovers long-standing conflicts and significant disputes, particularly over financial matters. These familial conflicts and mutual distrust could be directly related to the murder.",
            image: "resources/img/choice3_2.jpg",
            choices: [
                { text: "Trace the family's financial issues.", next: "choice3_2_1" },
                { text: "Investigate the family's alibis.", next: "choice3_2_2" },
                { text: "Attend a family gathering to overhear secret conversations.", next: "choice3_2_3" }
            ]
        },
        choice3_2_1: {
            text: "Tracing the family's financial issues, Detective Kang Junho discovers that the family was embroiled in a heated dispute over inheritance. Recent changes in the will had intensified the conflicts, suggesting that the distribution of assets was a significant source of tension.",
            image: "resources/img/choice3_2_1.jpg",
            choices: [],
            ending: {
                text: "The inheritance disputes provide a clear motive for one family member to commit the murder. Detective Kang Junho uncovers that a particular family member stood to gain significantly from the victim's death. With this evidence, the family member is arrested and confesses to the murder, successfully closing the case.",
                image: "resources/img/choice3_2_1ding.jpg"
            }
        },
        choice3_2_2: {
            text: "Investigating the family's alibis, Detective Kang Junho examines where each family member was on the day of the murder. While most alibis seem solid, one family member's alibi contains suspicious gaps that warrant further scrutiny.",
            image: "resources/img/choice3_2_2.jpg",
            choices: [],
            ending: {
                text: "Upon re-examining the suspicious alibi, Detective Kang Junho finds inconsistencies that suggest the family member was actually at the crime scene. Confronted with this evidence, the family member confesses to the murder, driven by the intense financial disputes and personal grudges within the family. This confession resolves the case.",
                image: "resources/img/choice3_2_2ding.jpg"
            }
        },
        choice3_2_3: {
            text: "Attending a family gathering, Detective Kang Junho overhears a secret conversation that reveals deep-seated mistrust and hidden grievances among family members. These conversations indicate that one of the family members had strong motives to eliminate the victim.",
            image: "resources/img/choice3_2_3.jpg",
            choices: [],
            ending: {
                text: "The overheard conversations expose the true nature of the familial conflicts and provide critical insights into the motives behind the murder. Detective Kang Junho uses this information to identify the primary suspect, who is then confronted and confesses to the crime. This confession, supported by the overheard evidence, ensures the successful resolution of the case.",
                image: "resources/img/choice3_2_3ding.jpg"
            }
        },
        choice3_3: {
            text: "Examining the relationships with the victim's colleagues, Detective Kang Junho discovers that the victim had intense rivalries with certain co-workers. These rivalries stemmed from professional competition and personal conflicts, suggesting that these colleagues could have motives for the murder.",
            image: "resources/img/choice3_3.jpg",
            choices: [
                { text: "Verify the alibis of the colleagues.", next: "choice3_3_1" },
                { text: "Investigate the last conflict between the victim and a colleague.", next: "choice3_3_2" },
                { text: "Search the victim's office for traces of interactions with colleagues.", next: "choice3_3_3" }
            ]
        },
        choice3_3_1: {
            text: "Verifying the alibis of the colleagues, Detective Kang Junho finds that one colleague's alibi is suspiciously vague. This colleague claims to have been at work all day but lacks concrete evidence to support the claim, raising red flags about their involvement.",
            image: "resources/img/choice3_3_1.jpg",
            choices: [],
            ending: {
                text: "The suspicious alibi leads Detective Kang Junho to scrutinize the colleague's activities further. Upon uncovering inconsistencies and additional evidence linking the colleague to the crime scene, the colleague is arrested and confesses to the murder, driven by professional rivalry and personal animosity towards the victim.",
                image: "resources/img/choice3_3_1ding.jpg"
            }
        },
        choice3_3_2: {
            text: "Investigating the last conflict between the victim and a colleague, Detective Kang Junho uncovers that the confrontation was more personal than initially thought. The colleague harbored deep resentment and had previously threatened the victim, indicating a strong motive for murder.",
            image: "resources/img/choice3_3_2.jpg",
            choices: [],
            ending: {
                text: "The investigation into the last conflict reveals that the colleague had a significant motive rooted in personal resentment. Detective Kang Junho gathers testimonies and evidence that link the colleague to the murder, leading to their arrest and confession. This resolution brings closure to the case.",
                image: "resources/img/choice3_3_2ding.jpg"
            }
        },
        choice3_3_3: {
            text: "Searching the victim's office, Detective Kang Junho finds documents and correspondence that highlight tense interactions with certain colleagues. These documents suggest that the victim was on the verge of exposing some unethical practices within the team, providing a motive for colleagues to eliminate him.",
            image: "resources/img/choice3_3_3.jpg",
            choices: [],
            ending: {
                text: "The documents found in the victim's office reveal plans to expose unethical practices, indicating why certain colleagues had motives to silence him. Detective Kang Junho uses this evidence to identify and arrest the primary suspect, who then confesses to the murder to protect their interests. This confession, supported by the documents, leads to the successful closure of the case.",
                image: "resources/img/choice3_3_3_ending.jpg"
            }
        }
    },
    ko: {
        start: {
            text: "강준호 형사는 오랜 세월 동안 쌓아온 경험과 예리한 직감을 바탕으로, 이번에 접수한 살인사건 현장으로 신속하게 발걸음을 옮긴다. 사건은 늦은 저녁, 조용하고 평화로운 주택가의 한 아파트에서 발생했으며, 피해자는 동네에서 평범하게 알려진 신뢰받는 회사원으로, 동료들과 이웃들 사이에서도 호감을 받고 있었다. 방 안은 깔끔하게 정돈되어 있었지만, 한 곳에서는 미세한 어긋남이 눈에 띄었다. 형사는 이 미세한 흔적들이 사건의 실마리를 제공할 것이라 확신하며, 범인이 철저하게 계획된 범죄를 저질렀음을 직감한다. 주변을 둘러싼 분위기는 긴장감으로 가득 차 있었고, 형사는 이 사건이 단순한 우발적 사고가 아님을 확신한다. 형사는 현장의 모든 요소를 면밀히 분석하며, 작은 디테일 하나하나가 사건 해결의 열쇠가 될 것임을 알고 있었다. 그는 사건의 배경과 피해자의 삶을 깊이 이해하기 위해 추가적인 조사를 계획한다.",
            image: "resources/img/start.jpg",
            choices: [
                { text: "방 안의 단서를 철저히 조사한다.", next: "choice1" },
                { text: "피해자의 과거를 조사해 본다.", next: "choice2" },
                { text: "주변 사람들의 알리바이를 확인한다.", next: "choice3" }
            ]
        },
        choice1: {
            text: "현장을 좀 더 면밀히 조사하기 위해 강준호 형사는 방 안의 각종 물건들을 하나하나 살펴보기 시작한다. 책상 위에는 회사 서류와 가정용품들이 어지럽게 놓여 있었고, 벽에는 다양한 사진들과 메모들이 부착되어 있었다. 형사는 이 물건들이 사건의 단서를 제공할 수 있다고 믿으며, 작은 디테일 하나하나를 놓치지 않으려 애쓴다. 특히, 사진 속 인물들과 메모의 내용이 무엇을 의미하는지 파악하려 노력하며, 방 안 곳곳에 숨겨진 의미를 찾기 위해 집중한다. 형사는 피해자의 생활 패턴과 습관을 이해하기 위해 물건들의 배치와 상태를 꼼꼼히 기록하며, 이를 통해 범인의 심리 상태와 범행 동기를 추론하려고 한다.",
            image: "resources/img/choice1.jpg",
            choices: [
                { text: "깨진 유리컵을 분석해 본다.", next: "choice1_1" },
                { text: "열린 창문을 통해 범인의 도주 경로를 추적한다.", next: "choice1_2" },
                { text: "방 안에 남겨진 메모를 해독한다.", next: "choice1_3" }
            ]
        },
        choice1_1: {
            text: "강준호 형사는 깨진 유리컵을 자세히 살펴보기 시작한다. 유리 조각에 묻은 미세한 흔적들과 함께, 컵의 손잡이 부분에서 이상한 흠집을 발견한다. 이 흠집은 누군가와의 격렬한 접촉을 암시하며, 사건의 배경에 깊은 갈등이 있었음을 시사한다. 형사는 이 흠집이 범인이 피해자와의 개인적인 감정으로 인해 발생했을 가능성을 염두에 두고, 이를 근거로 추가적인 조사를 계획한다.",
            image: "resources/img/choice1_1.jpg",
            choices: [
                { text: "유리컵에 남은 지문을 채취하여 분석한다.", next: "choice1_1_1" },
                { text: "컵이 깨진 이유를 추적해 본다.", next: "choice1_1_2" },
                { text: "유리컵 옆에 놓인 서류에 주목한다.", next: "choice1_1_3" }
            ]
        },
        choice1_1_1: {
            text: "형사는 유리컵에 남아 있던 지문을 조심스럽게 채취하여 분석을 의뢰한다. 지문 채취는 세심한 주의를 기울여 수행되었으며, 모든 과정에서 오염을 최소화하기 위해 노력했다. 며칠 후, 분석 결과가 도착했고, 그것은 놀라운 사실을 드러냈다. 지문은 근처에서 일하던 배달부의 것과 일치했다. 배달부는 평소 친절한 편이었지만, 이번 사건을 통해 그의 내면에 숨겨진 비밀이 드러날 조짐을 보이고 있었다. 형사는 배달부의 과거 기록과 주변 사람들의 증언을 바탕으로 그의 행동 패턴과 최근의 변화를 분석하기 시작했다.",
            image: "resources/img/choice1_1_1.jpg",
            choices: [
                { text: "배달부를 심문하여 지문이 남겨진 경위를 알아낸다.", next: "choice1_1_1_1" },
                { text: "배달부의 근무 기록을 조사하여 행적을 추적한다.", next: "choice1_1_1_2" },
                { text: "배달부의 주변 사람들을 조사하여 알리바이를 확인한다.", next: "choice1_1_1_3" }
            ]
        },
        choice1_1_1_1: {
            text: "배달부를 직접 심문한 강준호 형사는 그가 그날 밤 배달 업무를 마치고 집으로 돌아가는 길에 아파트에 들렀다고 진술한다. 그러나 그의 눈빛에는 불안감이 서려 있었고, 형사는 그가 무언가 숨기고 있음을 느낀다. 배달부는 평소와 다른 긴장된 표정을 짓고 있었으며, 자신의 행동에 대한 의문을 피하려는 듯한 태도를 보였다. 형사는 배달부의 말투와 몸짓에서 거짓의 흔적을 감지하며, 그의 진술을 더욱 면밀히 검토할 필요가 있다고 판단한다.",
            image: "resources/img/choice1_1_1_1.jpg",
            choices: [],
            ending: {
                text: "배달부는 결국 자신이 피해자와 말다툼을 하다 순간적으로 화를 참지 못하고 우발적으로 범행을 저질렀다고 고백한다. 그는 평소 피해자가 자신을 무시하고 모욕하는 발언을 일삼았으며, 그로 인해 격렬한 감정을 느꼈다고 주장했다. 사건은 단순한 감정의 폭발에서 비롯된 것으로 결론지어졌고, 강준호 형사는 배달부의 어두운 표정을 마지막으로 사건을 종결지었다. 배달부의 고백은 피해자의 주변인들에게도 충격을 주었으며, 형사는 그의 진술을 바탕으로 사건의 모든 경위를 명확히 이해하게 되었다. 이로써 형사는 단순한 범죄 사건을 넘어 인간의 복잡한 감정과 갈등이 얼마나 큰 비극을 초래할 수 있는지를 다시 한 번 깊이 느끼게 되었다. 피해자의 가족과 동료들은 슬픔 속에서도 형사의 노력에 감사의 뜻을 전했으며, 형사는 이번 사건을 통해 인간 심리에 대한 이해를 더욱 깊게 하게 되었다.",
                image: "resources/img/choice1_1_1_1ding.jpg"
            }
        },
        choice1_1_1_2: {
        	text: "배달부의 근무 기록을 조사한 강준호 형사는 그가 사건 전후에 일상적인 업무 외에도 의문의 시간을 보냈다는 사실을 발견한다. 그의 행적에는 석연치 않은 점들이 많아, 형사는 추가 조사가 필요함을 느낀다. 특히, 사건 당일과 그 전후의 시간대에 배달부가 자주 방문한 장소들이 드러났으며, 이는 그의 활동 범위와 관련된 중요한 단서를 제공한다. 형사는 배달부의 행적을 추적하며, 그의 의도와 범행 동기를 더 깊이 이해하려고 노력한다.",
        	image: "resources/img/choice1_1_1_2.jpg",
        	choices: [],
        	ending: {
            	text: "조사 끝에 배달부는 사건 당일 다른 범죄 조직과 연관되어 있었던 사실이 밝혀졌다. 그는 피해자의 집에 침입한 후 계획적으로 물건을 훔치려 했으나, 예상치 못한 충돌이 발생하면서 살인으로 이어졌다. 강준호 형사는 배달부의 조직과 연루된 범죄를 파헤치며, 더 깊은 음모가 있음을 알게 되었다. 이 과정에서 형사는 조직의 다른 구성원들과의 연결고리를 찾아내어, 사건의 배후에 있는 더 큰 범죄 네트워크를 밝혀내는 데 성공했다. 조직의 핵심 인물들을 체포함으로써, 형사는 단순한 범죄를 넘어선 조직의 어두운 면을 드러내며 지역 사회의 안전을 확보하는 데 기여했다. 또한, 배달부의 체포는 조직 내 다른 범죄 활동들을 밝히는 계기가 되었고, 형사는 그의 협조를 통해 더 많은 증거와 정보를 확보했다. 이로써 사건은 단순한 살인 사건을 넘어 조직 범죄의 심층적인 문제를 드러내는 중요한 계기가 되었다.",
            	image: "resources/img/choice1_1_1_2ding.jpg"
        }
    },
    choice1_1_1_3: {
        text: "배달부와 친분이 있는 사람들을 만나며, 그가 사건 전후에 이상한 행동을 보였다는 이야기를 듣는다. 그의 친구들은 배달부가 최근 몇 달 동안 이유 없는 불안과 스트레스를 겪고 있었다고 증언했다. 또한, 그는 종종 혼자만의 시간을 많이 가지며, 누군가와의 대화를 회피하는 경향이 있었다는 점도 강조되었다. 이러한 증언들은 배달부의 내면에 깊은 고민과 갈등이 있었음을 시사하며, 형사는 이를 바탕으로 그의 동기와 범행 배경을 더 깊이 이해하려고 했다.",
        image: "resources/img/choice1_1_1_3.jpg",
        choices: [],
        ending: {
            text: "배달부의 지인들은 그의 평소와는 다른 이상한 행동을 증언했다. 그는 사건 후 자주 술에 취해 다녔으며, 누군가를 피하는 듯한 모습을 보였다. 이러한 행동들은 배달부가 내부적으로 큰 스트레스와 죄책감을 느끼고 있었음을 암시했다. 강준호 형사는 배달부의 내면에 숨겨진 죄책감과 두려움을 간파하고, 결국 사건의 진실을 밝히며 배달부를 체포했다. 배달부는 자신의 행동에 대한 깊은 후회와 함께, 피해자와의 갈등이 어떻게 비극적인 결과로 이어졌는지를 설명하며 자신의 죄를 뉘우쳤다. 이로써 사건은 단순한 범죄를 넘어 인간의 복잡한 감정과 심리가 어떻게 비극을 초래할 수 있는지를 보여주는 교훈적인 사례로 남게 되었다. 형사는 이번 사건을 통해 인간 심리에 대한 깊은 이해와 더불어, 앞으로의 수사에서도 이러한 미묘한 감정과 동기들을 놓치지 않겠다는 다짐을 하게 되었다.",
            image: "resources/img/choice1_1_1_3ding.jpg"
        }
    },
    choice1_1_2: {
        text: "강준호 형사는 유리컵이 깨진 이유를 더욱 깊이 파악하기 위해 조사를 시작한다. 컵에 남은 실랑이 흔적과 함께, 범인의 급작스러운 움직임을 암시하는 흔적들이 포착된다. 형사는 이 흔적들이 범인의 긴박한 상황과 관련이 있음을 직감하며, 더욱 세밀한 조사를 계획한다.",
        image: "resources/img/choice1_1_2.jpg",
        choices: [
            { text: "유리 조각에서 혈흔을 찾아 분석한다.", next: "choice1_1_2_1" },
            { text: "유리컵을 깬 사람의 흔적을 추적한다.", next: "choice1_1_2_2" },
            { text: "유리컵이 깨진 상황을 재현해 본다.", next: "choice1_1_2_3" }
        ]
    },
    choice1_1_2_1: {
        text: "유리컵 조각에서 미세한 혈흔을 발견한 강준호 형사는 이를 신속하게 분석을 의뢰한다. 혈흔은 단순한 상처가 아닌, 특정한 질병을 앓고 있었던 흔적을 보여주었으며, 이는 범인이 특정한 건강 문제로 인해 긴박한 상황에 처해 있었음을 시사했다. 형사는 혈흔의 특성을 분석하여 범인의 신원을 좁혀 나가기로 결심했다.",
        image: "resources/img/choice1_1_2_1.jpg",
        choices: [],
        ending: {
            text: "혈흔 분석 결과, 범인은 피해자의 회사 동료로 밝혀졌다. 두 사람은 사소한 말다툼에서 시작된 갈등이 격화되면서 유리컵이 깨졌고, 동료는 우발적으로 피해자를 밀쳐 사망에 이르게 했다. 형사는 동료의 심리 상태를 분석하며, 그의 내면에 깊은 스트레스와 분노가 있었음을 발견했다. 사건의 진실이 밝혀지자 동료는 깊은 후회와 함께 자신의 죄를 인정했고, 법적 처벌을 받게 되었다. 강준호 형사는 이 사건을 통해 직장 내 갈등이 어떻게 비극적인 결과로 이어질 수 있는지를 다시 한 번 깨닫게 되었으며, 앞으로의 수사에서도 이러한 심리적 요인들을 더욱 면밀히 조사할 필요가 있음을 느끼게 되었다.",
            image: "resources/img/choice1_1_2_1ding.jpg"
        }
    },
    choice1_1_2_2: {
        text: "유리컵을 깬 사람의 흔적을 추적한 강준호 형사는 범인이 도주 중 컵을 깨트리며 작은 상처를 입은 것으로 보인다는 사실을 발견한다. 이 흔적은 범인의 급박한 상황을 암시하며, 형사는 범인의 신원을 파악하는 데 중요한 단서를 얻는다. 형사는 주변 CCTV와 목격자 증언을 통해 범인의 움직임을 추적하기 시작한다.",
        image: "resources/img/choice1_1_2_2.jpg",
        choices: [],
        ending: {
            text: "추적 끝에 범인의 신원이 드러났다. 그는 피해자의 이웃으로, 평소 피해자와 깊은 갈등을 겪고 있었다. 격렬한 다툼 중 유리컵을 깨트리면서 부상을 입었고, 사건 현장을 서둘러 떠났다. 형사는 CCTV와 주변 증언을 토대로 그의 도주 경로를 좁혀 나갔고, 결국 그의 은신처를 찾아내며 사건을 해결하게 되었다. 범인은 자신의 실수를 인정하며 자수를 선택했고, 형사는 이를 통해 지역 사회의 안정을 회복하는 데 기여했다. 이번 사건을 통해 형사는 범인의 심리 상태와 행동 패턴을 더욱 깊이 이해하게 되었으며, 앞으로의 수사에서도 이러한 분석을 바탕으로 사건 해결에 더욱 힘쓸 것을 다짐하게 되었다.",
            image: "resources/img/choice1_1_2_2ding.jpg"
        }
    },
    choice1_1_2_3: {
        text: "유리컵이 깨진 상황을 재현한 강준호 형사는 범인이 실수로 컵을 떨어뜨렸는지, 아니면 의도적으로 깼는지를 파악하려고 시도한다. 형사는 여러 차례의 재연을 통해 컵이 어떻게 깨졌는지를 분석하며, 다양한 변수들을 고려하여 사건의 가능성을 탐색한다.",
        image: "resources/img/choice1_1_2_3.jpg",
        choices: [],
        ending: {
            text: "재현을 통해, 유리컵은 의도적으로 깨진 것이 아닌 실수로 떨어진 것임이 밝혀졌다. 범인은 피해자와의 말다툼 도중 흥분하여 컵을 떨어뜨렸고, 그 과정에서 발생한 사고가 결국 사건으로 이어졌다. 형사는 범인이 계획적이지 않았음을 확인하고 사건을 종결지었다. 그러나 이번 사건을 통해 형사는 감정이 격해질 때의 인간 심리에 대해 깊이 고민하게 되었으며, 앞으로의 수사에서도 이러한 요소들을 더욱 면밀히 고려할 필요가 있음을 깨닫게 되었다.",
            image: "resources/img/choice1_1_2_3ding.jpg"
        }
    },
    choice1_1_3: {
        text: "유리컵 옆에 놓인 서류를 조사한 강준호 형사는 그것이 피해자가 다니던 회사와 관련된 중요한 문서임을 확인한다. 문서에는 회사의 내부 프로젝트와 관련된 정보들이 담겨 있었으며, 피해자가 이와 관련된 문제를 조사 중이었다는 단서가 숨겨져 있었다. 형사는 이 문서들이 사건과 어떤 연관이 있는지를 파악하기 위해 더욱 깊이 조사에 착수한다.",
        image: "resources/img/choice1_1_3.jpg",
        choices: [
            { text: "서류 속 특정 이름을 조사한다.", next: "choice1_1_3_1" },
            { text: "서류의 작성 날짜와 시간에 주목해 그때의 상황을 추적한다.", next: "choice1_1_3_2" },
            { text: "서류 속 암호를 해독해 숨겨진 정보를 파헤친다.", next: "choice1_1_3_3" }
        ]
    },
    choice1_1_3_1: {
        text: "서류에 등장하는 특정 이름을 조사한 강준호 형사는 그 인물이 피해자의 직장 동료였으며, 최근 큰 갈등을 겪고 있었다는 사실을 알게 된다. 두 사람은 회사 내에서 중요한 프로젝트를 두고 경쟁을 벌였고, 이로 인해 갈등이 심화되었다. 형사는 그 동료의 동기와 행동 패턴을 분석하여, 이번 사건과의 연관성을 파악하려고 했다.",
        image: "resources/img/choice1_1_3_1.jpg",
        choices: [],
        ending: {
            text: "서류 속 인물은 피해자의 직장 동료이자 라이벌이었다. 두 사람의 경쟁은 점점 심화되어 결국 직장 내 불화로 이어졌고, 그 갈등은 사적인 복수로 번지게 되었다. 형사는 이 사건이 단순한 직장 내 갈등에서 비롯된 비극임을 깨닫고, 해당 동료를 심문하여 그의 동기를 명확히 파악했다. 동료는 피해자에게 자신의 커리어에 위협을 느끼고 있었으며, 그로 인해 피해자를 제거하려는 의도를 가지고 있었다는 것이 밝혀졌다. 결국, 형사는 이 직장 내 갈등이 어떻게 비극적인 결과로 이어졌는지를 명확히 이해하게 되었고, 사건을 종결지을 수 있었다.",
            image: "resources/img/choice1_1_3_1ding.jpg"
        }
    },
    choice1_1_3_2: {
        text: "서류의 작성 날짜와 시간을 분석한 강준호 형사는 피해자가 그 시기에 회사 내에서 어떤 문제를 겪고 있었는지를 파악한다. 서류는 회사의 비리와 관련된 민감한 정보들을 담고 있었으며, 피해자는 이를 폭로하려는 계획을 세우고 있었다는 단서를 발견한다. 형사는 이 정보들이 사건과 어떻게 연결되어 있는지를 파악하기 위해 추가적인 조사를 계획했다.",
        image: "resources/img/choice1_1_3_2.jpg",
        choices: [],
        ending: {
            text: "서류 분석 결과, 피해자는 회사의 비리를 조사 중이었고, 그로 인해 위협을 받았다. 회사 내부의 음모가 얽혀 있었고, 피해자는 이를 폭로하려 했으나 그 과정에서 공격을 당했다. 형사는 피해자가 자신의 직업적 책임감 때문에 위험에 처해 있었다는 사실을 확인하고, 사건의 배후에 숨겨진 복잡한 이해관계를 풀어냈다. 피해자는 자신의 발견을 세상에 알리기 위해 싸웠지만, 그 과정에서 적을 만들고 결국 비극적인 결말을 맞이하게 되었다. 형사는 이 사실을 바탕으로 사건을 해결하며, 회사 내부의 부정과 비리 문제를 더욱 면밀히 조사하기로 결심했다.",
            image: "resources/img/choice1_1_3_2ding.jpg"
        }
    },
    choice1_1_3_3: {
        text: "서류 속 암호를 해독한 강준호 형사는 그것이 회사의 내부 비밀을 담고 있음을 알아낸다. 암호는 특정 프로젝트와 관련된 민감한 정보를 보호하기 위해 사용된 것으로 보이며, 이는 피해자가 회사의 비리를 폭로하려는 시도를 암시한다. 형사는 이 암호의 의미를 파악하고, 숨겨진 정보를 찾아내기 위해 더욱 깊이 조사에 착수했다.",
        image: "resources/img/choice1_1_3_3.jpg",
        choices: [],
        ending: {
            text: "암호를 해독한 결과, 피해자는 회사의 중요한 기밀을 알고 있었고, 이를 빌미로 협박을 당하고 있었다. 강준호 형사는 피해자가 내부 고발을 준비 중이었다는 사실을 알게 되었고, 사건의 배경에 숨겨진 복잡한 이해관계를 풀어냈다. 피해자는 회사의 비리를 폭로함으로써 자신의 직업적 윤리와 개인적 책임감 사이에서 갈등하고 있었으며, 이러한 갈등이 결국 그의 비극적인 죽음으로 이어졌다. 형사는 이로써 사건을 해결하게 되었으며, 피해자의 희생이 회사 내부의 부정을 드러내는 중요한 계기가 되었다는 것을 깨달았다. 또한, 형사는 이번 사건을 통해 내부 고발의 중요성과 그에 따른 위험성을 다시 한 번 깊이 인식하게 되었다.",
            image: "resources/img/choice1_1_3_3ding.jpg"
        }
    },
    choice1_2: {
        text: "열린 창문을 통해 범인의 도주 경로를 추적하기 시작한 강준호 형사는 창문 밖의 주변 환경을 면밀히 조사한다. 범인의 발자국이 남아 있지 않았지만, 창문 주변에 흩어진 물건들과 미세한 흔적들이 도주 경로에 대한 단서를 제공한다. 형사는 이러한 단서들을 종합하여 범인의 움직임을 추적하고, 그의 최종 목적지를 파악하기 위해 추가 조사를 계획했다.",
        image: "resources/img/choice1_2.jpg",
        choices: [
            { text: "정원에서 숨겨진 단서를 찾는다.", next: "choice1_2_1" },
            { text: "인근 CCTV를 확인해 도주 장면을 분석한다.", next: "choice1_2_2" },
            { text: "창문 근처에서 발견된 물건을 조사한다.", next: "choice1_2_3" }
        ]
    },
    choice1_2_1: {
        text: "정원을 꼼꼼히 조사한 강준호 형사는 범인이 도주하면서 남긴 작은 단서들을 발견한다. 정원의 흙 위에 미세하게 남은 신발 자국과 함께, 작은 조각들이 흩어져 있었다. 이 단서들은 범인의 이동 경로를 추적하는 데 중요한 정보를 제공했다. 형사는 이러한 단서들을 기반으로 범인의 발자국 패턴과 이동 속도를 분석하며, 범인이 향한 방향을 좁혀 나갔다.",
        image: "resources/img/choice1_2_1.jpg",
        choices: [
            { text: "신발 자국을 분석해 범인의 이동 경로를 추적한다.", next: "choice1_2_1_1" },
            { text: "발견된 조각을 조사해 범인의 도구와 연관성을 찾는다.", next: "choice1_2_1_2" },
            { text: "정원 주변을 탐색하며 추가적인 단서를 찾아본다.", next: "choice1_2_1_3" }
        ]
    },
    choice1_2_1_1: {
        text: "신발 자국을 분석한 강준호 형사는 범인이 도주한 경로를 추적하기 시작한다. 자국은 정원 문을 통해 빠져나가 인적이 드문 골목으로 이어지고 있었다. 형사는 이 골목이 범인의 최종 목적지임을 추측하며, 더 깊이 조사에 착수한다. 골목의 어둠 속에서 형사는 추가적인 단서를 찾기 위해 신중하게 움직였다.",
        image: "resources/img/choice1_2_1_1.jpg",
        choices: [],
        ending: {
            text: "추적 결과, 범인은 정원의 작은 문을 통해 빠져나가 어두운 골목으로 도주했다. 범인은 그 골목에서 대기 중이던 차량에 탑승해 흔적을 남기지 않으려 했으나, CCTV와 정원의 단서들이 그의 도주를 명확히 드러냈다. 형사는 범인의 도주 경로를 좁혀 나가며, 결국 그의 은신처를 찾아냈다. 범인은 자신이 저지른 일을 인정하며 자수하게 되었고, 형사는 사건을 해결하게 되었다. 이 과정에서 형사는 범인의 심리 상태와 도주 중의 행동 패턴을 분석하여, 향후 유사한 사건에서의 수사 전략을 더욱 정교하게 다듬을 수 있게 되었다.",
            image: "resources/img/choice1_2_1_1ding.jpg"
        }
    },
    choice1_2_1_2: {
        text: "정원에서 발견된 작은 조각들을 조사한 강준호 형사는 그것이 범인이 사용한 도구의 일부분임을 알아낸다. 이 조각들은 범인이 사건 당시 사용한 도구와 연관성이 높아 보이며, 도구의 기능과 사용 용도를 분석할 필요가 있다고 판단했다. 형사는 이러한 단서들이 범인의 의도와 행동 방식을 이해하는 데 중요한 역할을 할 것임을 깨닫고, 추가적인 조사를 계획했다.",
        image: "resources/img/choice1_2_1_2.jpg",
        choices: [],
        ending: {
            text: "조각들은 범인이 사건 당시 사용한 도구의 일부로 밝혀졌다. 이 도구는 피해자를 협박하거나 강제로 제압하는 데 사용된 것으로 보였다. 강준호 형사는 도구의 특성을 분석하여 범인의 정체를 파악하고, 사건의 진실을 밝혀내며 범인을 체포하게 되었다. 또한, 형사는 이 도구가 범인이 이전에도 사용한 수법과 일치함을 발견하여, 범인의 과거 범죄와의 연관성을 확인하게 되었다. 이를 통해 형사는 범인이 단순한 일회성 범죄자가 아니라, 조직적인 범죄 활동과 연결되어 있음을 깨닫고, 더 넓은 범죄 네트워크를 추적하기 시작했다.",
            image: "resources/img/choice1_2_1_2ding.jpg"
        }
    },
    choice1_2_1_3: {
        text: "정원 주변을 더 자세히 탐색한 강준호 형사는 추가적인 단서를 발견한다. 오래된 담배꽁초와 함께 누군가의 손수건이 버려져 있었고, 이는 범인이 급히 도망치면서 흩어진 물건들 중 하나로 보였다. 형사는 이 물건들이 범인의 신원을 특정하는 데 중요한 역할을 할 수 있음을 깨닫고, 손수건과 담배꽁초에서 남은 증거물을 분석하기로 했다.",
        image: "resources/img/choice1_2_1_3.jpg",
        choices: [],
        ending: {
            text: "손수건에는 범인의 DNA가 남아 있었고, 담배꽁초 역시 범인의 습관을 나타내는 중요한 단서였다. 이 단서들은 범인의 신원을 특정하는 데 결정적인 역할을 했으며, 형사는 이를 통해 범인을 추적해 사건을 해결하게 되었다. DNA 분석 결과, 범인은 지역 내에서 이전에도 범죄를 저질렀던 전력이 있는 인물로 확인되었고, 그의 행적을 추적하여 최종적으로 체포에 성공했다. 이번 사건을 통해 형사는 과학적 증거의 중요성을 다시 한 번 깨달았으며, 앞으로의 수사에서도 이러한 단서들을 더욱 효과적으로 활용할 것을 다짐하게 되었다.",
            image: "resources/img/choice1_2_1_3ding.jpg"
        }
    },
    choice1_2_2: {
        text: "인근 CCTV 영상을 확인한 강준호 형사는 범인의 도주 장면을 분석한다. 영상 속에서 범인은 얼굴을 가리고 있었지만, 그의 독특한 행동 패턴과 움직임을 통해 중요한 단서를 발견했다. 형사는 이 행동 패턴이 특정 범죄 조직이나 이전의 범죄 행위와 연결될 수 있음을 직감하며, 이를 바탕으로 더욱 깊이 있는 조사를 시작했다.",
        image: "resources/img/choice1_2_2.jpg",
        choices: [
            { text: "행동 패턴을 분석해 범인의 과거 범죄와의 연관성을 찾는다.", next: "choice1_2_2_1" },
            { text: "CCTV 영상을 복원하여 범인의 더 많은 움직임을 확인한다.", next: "choice1_2_2_2" },
            { text: "영상 속 범인의 동선을 따라가며 추가적인 단서를 찾는다.", next: "choice1_2_2_3" }
        ]
    },
    choice1_2_2_1: {
        text: "범인의 특이한 행동 패턴을 분석한 강준호 형사는 과거에 유사한 사건을 저질렀던 용의자와의 연결점을 찾아낸다. 이 패턴은 특정 범죄 조직과의 연관성을 시사하며, 형사는 이를 바탕으로 추가 조사를 진행했다. 형사는 과거 사건들의 기록을 면밀히 검토하며, 범인이 이전에도 같은 수법으로 범행을 저질렀는지를 확인하기 시작했다.",
        image: "resources/img/choice1_2_2_1.jpg",
        choices: [],
        ending: {
            text: "행동 패턴 분석을 통해 범인은 과거에도 유사한 수법으로 범행을 저질렀던 전력이 있는 것으로 드러났다. 강준호 형사는 과거의 사건들과 연관된 인물을 추적하며, 이번 사건 역시 같은 범인이 저지른 것임을 밝혀냈다. 추가적인 증거와 증언을 통해 범인의 조직적 배후가 드러났으며, 형사는 이 조직과의 연결고리를 찾아내어 더 큰 범죄 네트워크를 폭로하는 데 성공했다. 결국 범인은 체포되어 과거의 죄와 함께 심판을 받게 되었고, 형사는 이로써 지역 사회의 안전을 확보하는 데 기여하게 되었다.",
            image: "resources/img/choice1_2_2_1ding.jpg"
        }
    },
    choice1_2_2_2: {
        text: "손상된 CCTV 영상을 복원한 강준호 형사는 범인의 도주 경로를 더욱 명확히 파악할 수 있게 된다. 복원된 영상은 범인이 도주하는 순간을 선명하게 보여주며, 그의 움직임과 방향성을 파악하는 데 큰 도움이 되었다. 형사는 이를 바탕으로 범인의 행동 패턴과 이동 경로를 분석하며, 도주 과정에서의 실수나 단서를 찾아내려 했다.",
        image: "resources/img/choice1_2_2_2.jpg",
        choices: [],
        ending: {
            text: "복원된 영상은 범인이 도주하는 순간을 더욱 선명하게 드러냈다. 범인은 CCTV 사각지대를 철저히 이용했지만, 결정적인 순간을 피하지 못했다. 영상에는 범인의 도주 경로와 행동 패턴이 명확히 보였으며, 이를 토대로 강준호 형사는 범인의 도주 경로를 좁혀 나갔다. 형사는 범인의 이동을 추적하며, 결국 그의 은신처를 찾아내어 체포에 성공했다. 이번 사건을 통해 형사는 과학적 증거의 중요성과 기술의 발전이 수사에 얼마나 큰 도움이 되는지를 다시 한 번 깨달았으며, 앞으로의 수사에서도 이러한 도구들을 적극적으로 활용할 것을 다짐하게 되었다.",
            image: "resources/img/choice1_2_2_2ding.jpg"
        }
    },
    choice1_2_2_3: {
        text: "영상 속에서 범인이 지나간 장소를 추적한 강준호 형사는 현장에서 추가적인 단서를 발견했다. 범인이 지나간 자리에는 잃어버린 개인 소지품과 더불어 중요한 증거들이 남아 있었다. 형사는 이러한 단서들을 수집하여 범인의 신원을 특정하는 데 활용하려 했다.",
        image: "resources/img/choice1_2_2_3.jpg",
        choices: [],
        ending: {
            text: "강준호 형사는 CCTV에 기록된 범인의 이동 경로를 따라가며, 그가 지나간 장소에서 중요한 증거들을 찾아냈다. 발견된 소지품 중에는 범인의 신원과 연결될 수 있는 개인 물품들이 있었으며, 추가적인 DNA 분석을 통해 범인의 정체가 확정되었다. 범인은 도주 중 실수를 저질렀고, 그로 인해 남긴 단서들이 사건을 해결하는 열쇠가 되었다. 결국 강준호 형사는 범인을 체포하며 사건을 종결지었다. 이번 사건을 통해 형사는 꼼꼼한 증거 수집과 분석의 중요성을 다시 한 번 인식하게 되었으며, 앞으로의 수사에서도 이러한 방법들을 더욱 효과적으로 활용할 것을 다짐하게 되었다.",
            image: "resources/img/choice1_2_2_3ding.jpg"
        }
    },
    choice1_2_3: {
        text: "창문 근처에서 발견된 작은 물건을 조사한 강준호 형사는 그것이 범인이 급히 도망치면서 떨어뜨린 물건임을 확인한다. 물건에는 특정 문양이 새겨져 있었으며, 이는 범인의 소속과 관련된 중요한 단서를 제공했다. 형사는 이 문양의 출처를 파악하고, 이를 통해 범인의 조직적 배후를 조사하기 시작했다.",
        image: "resources/img/choice1_2_3.jpg",
        choices: [
            { text: "문양의 출처를 파악해 조직의 정보를 수집한다.", next: "choice1_2_3_1" },
            { text: "물건을 통해 범인의 신원을 특정한다.", next: "choice1_2_3_2" },
            { text: "조직의 일원을 만나 정보를 얻어낸다.", next: "choice1_2_3_3" }
        ]
    },
    choice1_2_3_1: {
        text: "문양의 출처를 파악한 강준호 형사는 그것이 특정 범죄 조직의 상징임을 알아냈다. 형사는 이 조직이 지역 내에서 여러 범죄를 저지르고 있었다는 정보를 수집하며, 조직의 구조와 활동을 파헤치기 시작했다. 형사는 조직의 주요 인물들과 그들의 역할을 분석하고, 조직의 동향을 파악하기 위해 다양한 수사 기법을 동원했다.",
        image: "resources/img/choice1_2_3_1.jpg",
        choices: [],
        ending: {
            text: "문양은 특정 범죄 조직의 상징으로 밝혀졌고, 이 조직은 지역 내에서 여러 범죄를 저지르고 있었다. 강준호 형사는 조직과의 연결고리를 파헤쳐 범인이 조직의 일원임을 밝혀냈고, 결국 조직의 핵심 인물을 체포하며 사건을 종결했다. 조직 내부의 정보원과의 협력을 통해, 형사는 조직의 다른 범죄 활동들도 동시에 수사할 수 있게 되었고, 지역 사회의 안전을 확보하는 데 크게 기여했다. 또한, 형사는 이 조직의 구조와 운영 방식을 분석하여 향후 유사한 범죄에 대한 예방과 대처 방안을 마련하는 데 중요한 기초 자료를 확보하게 되었다.",
            image: "resources/img/choice1_2_3_1ding.jpg"
        }
    },
    choice1_2_3_2: {
        text: "물건에 남아 있던 미세한 지문과 단서를 통해 강준호 형사는 범인의 신원을 특정해 나간다. 지문 분석과 추가 단서들을 조합하여, 범인의 정체가 점점 명확해지기 시작했다. 형사는 이 단서들이 범인의 배경과 동기를 이해하는 데 중요한 역할을 할 것임을 깨달았다.",
        image: "resources/img/choice1_2_3_2.jpg",
        choices: [],
        ending: {
            text: "지문 분석 결과, 범인은 조직에 소속된 인물로, 과거에도 여러 범죄에 연루된 전력이 있었다. 물건에 남아 있는 흔적들이 범인의 정체를 밝히는 중요한 단서가 되었고, 강준호 형사는 이를 통해 범인을 추적해 체포했다. 범인은 조직의 지시를 받고 피해자를 제거하려 했으며, 그의 범죄 행위는 조직의 더 큰 범죄 활동과 연관되어 있었다. 형사는 범인을 체포하면서 조직의 다른 구성원들과의 연결고리를 찾아내어, 조직 전체를 타격하는 데 성공했다. 이로써 지역 사회는 더욱 안전해졌으며, 형사는 자신의 수사 능력과 끈기가 이번 사건을 성공적으로 해결하는 데 큰 도움이 되었음을 실감했다.",
            image: "resources/img/choice1_2_3_2ding.jpg"
        }
    },
    choice1_2_3_3: {
        text: "조직의 일원을 찾아가 강압적인 심문을 진행한 강준호 형사는 사건에 대한 더 많은 정보를 얻어냈다. 조직의 일원은 범인이 조직의 지시를 받고 행동했음을 암시하며, 사건의 배후에 있는 인물들의 정체를 드러냈다. 형사는 조직 내부의 신뢰할 수 있는 인물을 통해 추가 정보를 확보하고, 조직의 구조와 운영 방식을 파악하기 위해 더욱 심층적인 조사를 계획했다.",
        image: "resources/img/choice1_2_3_3.jpg",
        choices: [],
        ending: {
            text: "조직의 일원과의 대화에서 강준호 형사는 범인이 조직의 지시를 받고 행동했음을 알아냈다. 조직은 피해자를 표적으로 삼았고, 사건은 치밀하게 계획된 것이었다. 형사는 조직의 배후를 밝혀내며 사건을 마무리했다. 추가적인 증거와 증언을 통해, 형사는 조직의 주요 인물들과 그들의 동기를 파악할 수 있었으며, 이를 바탕으로 조직 전체를 붕괴시키는 데 성공했다. 이 과정에서 형사는 조직 내부의 배신과 음모를 밝혀내며, 조직의 진정한 목표와 범죄 활동의 규모를 파악하게 되었다. 결국, 형사의 노력으로 조직의 모든 주요 인물이 체포되었고, 지역 사회는 한층 더 안전해졌다. 이번 사건을 통해 형사는 조직 범죄의 복잡성과 그에 따른 수사의 중요성을 다시 한 번 깨닫게 되었으며, 앞으로의 수사에서도 이러한 경험을 바탕으로 더욱 효과적으로 범죄를 해결할 수 있을 것임을 확신하게 되었다.",
            image: "resources/img/choice1_2_3_3ding.jpg"
        }
    },
    choice1_3: {
        text: "방 안에 남겨진 작은 메모를 발견한 강준호 형사는 그것이 단순한 메시지가 아닌, 사건의 중요한 단서임을 직감한다. 메모에는 '늦은 밤의 진실을 찾아라'는 문장이 적혀 있었고, 이는 사건의 숨겨진 진실을 찾아야 한다는 강력한 힌트를 제공했다. 형사는 이 메모가 단순한 메시지가 아닌, 사건의 배후를 파악하는 데 중요한 역할을 할 것임을 깨닫고, 추가적인 조사를 계획했다.",
        image: "resources/img/choice1_3.jpg",
        choices: [
            { text: "밤에 현장을 다시 조사한다.", next: "choice1_3_1" },
            { text: "메모의 문장을 분석해 숨겨진 의미를 찾는다.", next: "choice1_3_2" },
            { text: "메모의 필적을 분석해 남긴 사람을 찾는다.", next: "choice1_3_3" }
        ]
    },
    choice1_3_1: {
        text: "메모의 힌트를 따라 밤 시간에 현장을 다시 찾은 강준호 형사는 어둠 속에서 더욱 많은 단서들을 발견한다. 밤의 정적 속에서 빛나는 증거물들이 사건의 숨겨진 진실을 밝혀내는 열쇠가 되었다. 형사는 현장의 조명이 꺼진 구석구석을 탐색하며, 밤에만 보이는 단서들을 찾아내기 위해 더욱 세심하게 조사했다.",
        image: "resources/img/choice1_3_1.jpg",
        choices: [],
        ending: {
            text: "밤에 현장을 다시 조사한 결과, 사건의 숨겨진 흔적들이 보이기 시작했다. 어둠 속에서 찾은 단서들이 사건의 진실을 밝혀내는 열쇠가 되었고, 강준호 형사는 이내 사건의 전모를 이해하게 되었다. 그는 범인의 실수를 발견하며 사건을 종결지었다. 발견된 단서들은 범인이 계획적으로 범행을 저질렀음을 시사했으며, 형사는 이를 통해 범인의 동기와 행동 패턴을 명확히 파악할 수 있었다. 추가적인 증거 수집을 통해 범인의 정체를 특정하고, 범인을 체포함으로써 사건은 성공적으로 해결되었다. 형사는 이번 사건을 통해 밤 시간에 수집된 단서들이 얼마나 중요한지를 깨달았으며, 앞으로의 수사에서도 이러한 전략을 적극적으로 활용할 것을 다짐하게 되었다.",
            image: "resources/img/choice1_3_1ding.jpg"
        }
    },
    choice1_3_2: {
        text: "메모의 문장을 분석한 강준호 형사는 그것이 단순한 메시지가 아닌, 범인이 남긴 경고임을 알아낸다. 문장 속에는 사건의 배경과 범인의 의도가 담겨 있었으며, 이를 통해 더 깊은 단서를 추적할 수 있었다. 형사는 이 문장 속에 숨겨진 암호나 상징을 해독하며, 사건의 전말을 파악하려 했다.",
        image: "resources/img/choice1_3_2.jpg",
        choices: [],
        ending: {
            text: "메모의 문장은 단순한 메시지가 아닌, 범인이 남긴 경고였다. 피해자는 사건 전에 이미 위협을 받았고, 그 위협은 결국 그의 목숨을 앗아갔다. 강준호 형사는 이 경고의 의미를 파악하고, 사건의 배후에 있는 자를 밝혀내며 사건을 해결했다. 문장의 암호를 해독함으로써, 형사는 범인이 피해자에게 어떠한 의도를 가지고 있었는지를 명확히 이해하게 되었고, 이를 통해 범인의 동기와 행동을 정확히 추적할 수 있었다. 이번 사건은 단순한 범죄를 넘어 복잡한 인간 심리와 감정이 어떻게 비극적인 결과를 초래할 수 있는지를 보여주는 중요한 사례로 남게 되었으며, 형사는 이러한 심리적 요인들을 고려한 수사의 중요성을 다시 한 번 깨닫게 되었다.",
            image: "resources/img/choice1_3_2ding.jpg"
        }
    },
    choice1_3_3: {
        text: "메모의 필적을 분석한 강준호 형사는 그 글씨체와 쓰기 습관을 통해 남긴 사람이 범인임을 특정해 나간다. 필적 분석은 전문가의 도움을 받아 수행되었으며, 형사는 이를 통해 범인의 정체를 밝히기 위한 중요한 단서를 얻을 수 있었다.",
        image: "resources/img/choice1_3_3.jpg",
        choices: [],
        ending: {
            text: "메모의 필적 분석을 통해 남긴 사람이 범인임이 밝혀졌다. 그는 피해자에게 사전에 경고를 보냈지만, 결국 행동에 옮기고 말았다. 강준호 형사는 필적을 통해 범인의 정체를 밝히고 그를 체포하며 사건을 마무리했다. 범인은 피해자와의 갈등 속에서 자신의 분노와 실망을 참지 못하고 범행을 저질렀으며, 형사는 그의 심리 상태를 분석하여 사건의 배경을 명확히 이해하게 되었다. 이번 사건은 형사에게 인간 심리의 복잡성과 수사의 중요성을 다시 한 번 일깨워 주었으며, 앞으로의 수사에서도 이러한 요소들을 더욱 정밀하게 분석하고 활용할 것을 다짐하게 되었다.",
            image: "resources/img/choice1_3_3ding.jpg"
        }
    },
    choice2: {
        text: "피해자의 과거를 조사한 강준호 형사는 그가 직장 내에서 많은 다툼과 스트레스를 겪었다는 사실을 알게 된다. 피해자는 동료들과의 갈등 속에서 심리적 압박을 받았으며, 이는 그의 일상에 큰 영향을 미쳤다. 형사는 이러한 스트레스가 사건과 어떤 관련이 있는지 파악하기 위해 추가 조사를 시작했다. 형사는 피해자의 일상과 직장 생활을 면밀히 분석하며, 그가 어떤 상황에서 스트레스를 받았는지, 그리고 그 스트레스가 사건에 어떻게 영향을 미쳤는지를 이해하려 했다.",
        image: "resources/img/choice2.jpg",
        choices: [
            { text: "최근 동료들과의 관계를 조사한다.", next: "choice2_1" },
            { text: "피해자가 얽혔던 사내 문제를 파헤친다.", next: "choice2_2" },
            { text: "피해자의 가족과 친구들을 만나 과거를 파악한다.", next: "choice2_3" }
        ]
    },
    choice2_1: {
        text: "최근 동료들과의 관계를 조사한 강준호 형사는 그 중 한 명이 피해자와의 다툼 이후 큰 앙심을 품고 있었다는 사실을 발견했다. 이 동료는 피해자와의 경쟁에서 패배한 후, 그의 성공에 대해 질투심을 느끼고 있었다. 형사는 이 동료의 과거 행동과 동기를 분석하며, 그의 행동이 이번 사건과 어떤 관련이 있는지를 파악하려 했다.",
        image: "resources/img/choice2_1.jpg",
        choices: [
            { text: "동료를 직접 심문한다.", next: "choice2_1_1" },
            { text: "동료의 알리바이를 확인한다.", next: "choice2_1_2" },
            { text: "동료와 피해자가 나눈 이메일과 메시지를 조사한다.", next: "choice2_1_3" }
        ]
    },
    choice2_1_1: {
        text: "동료를 직접 심문한 강준호 형사는 그가 협박을 받았다고 주장하며, 사건이 다른 사람과 얽혀 있음을 암시한다. 동료는 피해자와의 갈등이 단순한 직장 내 문제가 아니었음을 시사하며, 더 깊은 진실이 숨겨져 있음을 알렸다. 형사는 동료의 말 속에서 모순된 점을 발견하고, 그의 진술을 더욱 면밀히 검토하기 시작했다.",
        image: "resources/img/choice2_1_1.jpg",
        choices: [],
        ending: {
            text: "동료는 사건 전 협박을 당했고, 그로 인해 피해자와의 갈등이 격화되었다고 진술했다. 형사는 동료의 주장 속에 숨겨진 진실을 파헤쳐, 협박의 배후가 사건과 연관이 있음을 밝혀냈다. 협박은 피해자의 직장 내 비리와 연관된 외부 인물에 의해 이루어졌으며, 이는 사건의 배후에 더 큰 음모가 있음을 시사했다. 형사는 추가적인 증거를 수집하며, 협박의 배후에 있는 인물들을 추적하기 시작했다. 이 과정에서 형사는 조직적인 범죄 네트워크의 존재를 확인하게 되었고, 사건은 단순한 직장 내 갈등을 넘어선 복잡한 음모로 드러났다.",
            image: "resources/img/choice2_1_1ding.jpg"
        }
    },
    choice2_1_2: {
        text: "동료의 알리바이를 철저히 조사한 강준호 형사는 사건 당일 그의 알리바이가 일부 조작되었음을 발견했다. 동료는 완벽한 알리바이를 제시했지만, 실제로는 피해자와의 만남이 있었다는 단서를 발견하게 되었다. 형사는 동료의 알리바이를 다시 검토하며, 그의 움직임을 추적하기 시작했다.",
        image: "resources/img/choice2_1_2.jpg",
        choices: [],
        ending: {
            text: "조사 끝에 동료의 알리바이는 일부 조작된 것으로 드러났다. 그는 사건 당일 피해자와 마주쳤고, 그 만남이 사건의 발단이 되었다. 동료는 자신이 피해자에게 어떤 요구를 했으며, 그것이 왜 충돌로 이어졌는지를 설명했다. 결국, 동료는 자신의 잘못을 인정하며 자수를 결심했다. 형사는 이로써 사건의 주요 용의자를 확보할 수 있었으며, 사건은 단순한 직장 내 갈등을 넘어선 범죄로 결론지어졌다. 동료의 자수는 사건 해결에 큰 도움이 되었고, 형사는 그의 진술을 통해 사건의 전모를 명확히 파악할 수 있게 되었다.",
            image: "resources/img/choice2_1_2ding.jpg"
        }
    },
    choice2_1_3: {
        text: "동료와 피해자가 주고받은 이메일과 메시지를 조사한 강준호 형사는 두 사람 간의 긴장과 갈등이 점점 심화되었음을 확인할 수 있었다. 메시지 속에는 협박과 위협적인 내용들이 담겨 있었으며, 이는 사건의 배경을 이해하는 데 중요한 단서를 제공한다. 형사는 이러한 메시지들이 단순한 오해에서 비롯된 것이 아니라, 의도적으로 피해자를 압박하거나 위협하기 위한 수단으로 사용되었을 가능성을 염두에 두고 조사에 착수한다.",
        image: "resources/img/choice2_1_3.jpg",
        choices: [],
        ending: {
            text: "이메일과 메시지에서 피해자와 동료 간의 갈등이 격화되었음을 확인할 수 있었다. 동료는 피해자에게 마지막 경고를 보냈고, 그 경고는 현실이 되어버렸다. 형사는 메시지의 발신자와 수신자를 철저히 분석하여, 동료가 피해자에게 어떤 식으로 협박을 가했는지를 상세히 파악했다. 추가적으로, 형사는 메시지 속에 숨겨진 암호나 코드가 있는지 검토했으며, 이를 통해 사건의 배후에 숨겨진 의도와 계획을 밝혀내기 위해 노력했다. 결국, 동료가 피해자를 위협하기 위해 사용한 구체적인 문구와 시간대를 기반으로 동료의 알리바이를 다시 한번 재검토하게 되었고, 이를 통해 동료가 범행을 저질렀을 가능성이 높다는 결론에 도달했다. 강준호 형사는 이러한 증거들을 종합하여 동료를 체포하고, 그의 자백을 통해 사건의 진실을 완전히 파악하게 되었다.",
            image: "resources/img/choice2_1_3ding.jpg"
        }
    },
    choice2_2: {
        text: "피해자가 얽혔던 사내 문제를 조사한 강준호 형사는 피해자가 회사 내에서 여러 가지 갈등과 문제로 인해 심각한 스트레스를 많이 받았다는 사실을 알게 된다. 피해자는 회사의 중요한 프로젝트를 맡고 있었으며, 이로 인해 동료들과의 갈등이 심화되었다. 형사는 이러한 갈등이 사건과 어떤 관련이 있는지를 파악하기 위해 추가 조사를 시작한다. 그는 피해자의 업무 기록과 프로젝트 관련 문서를 분석하며, 피해자가 직면했던 구체적인 문제점과 갈등의 원인을 탐색한다.",
        image: "resources/img/choice2_2.jpg",
        choices: [
            { text: "피해자가 받았던 경고 메시지를 추적한다.", next: "choice2_2_1" },
            { text: "사내 문제와 관련된 인물을 찾아간다.", next: "choice2_2_2" },
            { text: "피해자가 남긴 업무 기록을 조사한다.", next: "choice2_2_3" }
        ]
    },
    choice2_2_1: {
        text: "피해자가 받은 경고 메시지를 추적한 강준호 형사는 그 메시지의 출처를 확인하기 시작한다. 메시지는 회사 내에서 경쟁 관계에 있는 한 동료로부터 온 것으로 밝혀지며, 그 동료는 피해자가 자신의 비밀을 폭로할 것을 두려워해 위협을 가한 것으로 추정된다. 형사는 메시지의 발신자를 정확히 식별하기 위해 이메일 서버 로그와 휴대폰 기록을 분석하며, 메시지의 내용과 발송 시간을 상세히 검토한다.",
        image: "resources/img/choice2_2_1.jpg",
        choices: [],
        ending: {
            text: "경고 메시지는 회사 내의 경쟁자가 보낸 것이었다. 그는 피해자가 자신의 비밀을 폭로할 것을 두려워해 위협을 가했다. 형사는 메시지의 내용에서 그가 피해자에게 어떤 정보를 숨기고 있었는지를 파악했고, 이를 바탕으로 동료의 동기와 범행 계획을 분석했다. 추가 조사를 통해 동료가 피해자의 비밀을 파헤치려는 피해자의 행동을 견제하기 위해 위협을 가했음을 확인했다. 결국, 동료는 자신의 잘못된 판단과 감정에 의해 사건을 일으켰다는 것을 인정하며 자수를 결심했고, 형사는 이로써 사건을 마무리 지을 수 있었다.",
            image: "resources/img/choice2_2_1ding.jpg"
        }
    },
    choice2_2_2: {
        text: "사내 문제와 관련된 인물을 찾아가 심문한 강준호 형사는 그 인물이 사건의 열쇠를 쥐고 있음을 확인했다. 이 인물은 피해자와의 갈등 속에서 극단적인 선택을 했으며, 그 선택이 결국 사건으로 이어졌음을 시사했다. 형사는 이 인물의 동기와 배경을 철저히 조사하며, 그의 행동이 어떻게 사건과 연결되는지를 분석한다.",
        image: "resources/img/choice2_2_2.jpg",
        choices: [],
        ending: {
            text: "심문 끝에 사내 문제와 얽힌 인물은 사건의 열쇠를 쥐고 있었다. 그는 피해자와의 갈등 속에서 극단적인 선택을 했고, 그 선택이 결국 사건으로 이어졌다. 형사는 이 인물이 피해자와의 갈등에서 어떤 구체적인 사건들이 있었는지를 파악했고, 그의 행동이 어떻게 비극적인 결과로 이어졌는지를 명확히 이해했다. 추가적인 증거를 확보한 형사는 이 인물을 체포하고, 그의 자백을 통해 사건의 전모를 밝히며, 피해자의 고통과 갈등이 어떻게 비극으로 이어졌는지를 완전히 이해하게 되었다.",
            image: "resources/img/choice2_2_2ding.jpg"
        }
    },
    choice2_2_3: {
        text: "피해자가 남긴 업무 기록을 조사한 강준호 형사는 그가 마지막으로 겪었던 갈등의 내막을 파헤치기 시작한다. 업무 기록에는 회사 내부의 부정과 관련된 중요한 정보들이 담겨 있었으며, 피해자는 이를 조사하던 중 위협을 받았을 가능성이 높아 보인다. 형사는 이러한 기록들을 통해 피해자가 어떤 부정과 문제에 직면했는지를 상세히 파악하며, 그로 인해 발생한 갈등이 사건과 어떻게 연결되는지를 분석한다.",
        image: "resources/img/choice2_2_3.jpg",
        choices: [],
        ending: {
            text: "업무 기록은 피해자가 회사 내부의 부정을 조사 중이었다는 사실을 드러냈다. 그는 자신의 직장을 지키기 위해 싸웠지만, 그 과정에서 적을 만들었다. 형사는 피해자가 조사하던 부정의 내용과 관련된 증거들을 추가로 확보하며, 그가 왜 위협을 받았는지를 명확히 이해하게 되었다. 결국, 피해자가 정당성을 지키려다 희생된 비극으로 사건은 마무리되었다. 형사는 이 사건을 통해 회사 내부의 부정이 어떻게 개인의 삶을 파괴할 수 있는지를 깊이 인식하게 되었으며, 피해자의 희생이 헛되지 않도록 철저히 조사를 마쳤다.",
            image: "resources/img/choice2_2_3ding.jpg"
        }
    },
    choice2_3: {
        text: "피해자의 가족과 친구들을 만나 과거를 조사한 강준호 형사는 피해자의 주변에 여러 감정의 얽힘과 갈등이 있었음을 발견했다. 피해자는 가족과의 관계에서도 갈등을 겪고 있었으며, 친구들 사이에서도 복잡한 감정들이 존재했다. 형사는 이러한 감정의 얽힘이 사건과 어떤 관련이 있는지를 파악하기 위해, 각 가족 구성원과 친구들을 면밀히 조사하기로 결심한다.",
        image: "resources/img/choice2_3.jpg",
        choices: [
            { text: "피해자의 배우자와 마지막 대화를 재현한다.", next: "choice2_3_1" },
            { text: "피해자의 친구가 가진 비밀을 추적한다.", next: "choice2_3_2" },
            { text: "피해자가 남긴 유언장을 조사한다.", next: "choice2_3_3" }
        ]
    },
    choice2_3_1: {
        text: "피해자의 배우자와 마지막 대화를 재현한 강준호 형사는 그 대화 속에서 피해자가 죽기 전 느꼈던 공포와 혼란을 깊이 이해하게 된다. 대화 내용은 피해자가 자신에게 큰 부담과 위협을 느끼고 있었음을 암시하며, 이는 사건의 중요한 단서로 작용할 수 있다. 형사는 대화의 맥락과 감정의 변화를 분석하여, 피해자가 어떤 상황에서 위협을 받았는지를 파악하려 한다.",
        image: "resources/img/choice2_3_1.jpg",
        choices: [],
        ending: {
            text: "배우자와의 대화에서 피해자는 두려움에 사로잡혀 있었다. 그는 자신의 안전을 걱정했고, 그것이 현실로 다가왔다. 형사는 피해자의 마지막 감정들을 이해하며, 그가 어떤 위협을 받았는지를 명확히 파악했다. 추가적인 조사 결과, 피해자가 직장 내에서 조사 중이던 부정과 관련된 위협을 받았으며, 이러한 위협이 결국 그의 생명을 앗아가는 계기가 되었음을 밝혀냈다. 형사는 피해자의 배우자가 실제로 사건에 어떤 역할을 했는지를 철저히 조사했고, 그의 진술을 통해 사건의 전모를 완전히 이해하게 되었다. 피해자의 마지막 순간들을 재현하며, 형사는 그가 겪었던 공포와 혼란의 진상을 밝혀내어 사건을 성공적으로 해결했다.",
            image: "resources/img/choice2_3_1ding.jpg"
        }
    },
    choice2_3_2: {
        text: "피해자의 친구가 가진 비밀을 추적한 강준호 형사는 그 친구가 피해자와의 깊은 우정을 나누던 중에 숨겨진 비밀을 알고 있었음을 발견했다. 이 비밀은 피해자가 직장과 가정에서 겪었던 고통과 관련이 깊으며, 친구는 피해자의 상황을 알고 있었지만, 그것을 숨기려 했던 것으로 보인다. 형사는 친구가 왜 이 비밀을 숨기려 했는지를 파악하기 위해 추가적인 조사를 진행한다.",
        image: "resources/img/choice2_3_2.jpg",
        choices: [],
        ending: {
            text: "친구는 피해자가 평소 고민을 털어놓았던 유일한 사람이었다. 그는 피해자가 직장과 가정에서 겪었던 고통을 알고 있었고, 그 사실이 사건과 직결되어 있었다. 형사는 친구의 증언을 통해 피해자가 직면했던 스트레스와 갈등의 깊이를 이해하게 되었다. 또한, 친구는 피해자가 직장 내 부정과 관련된 위협을 받았다는 사실을 알고 있었으며, 피해자를 돕기 위해 노력했지만, 결국 그를 보호하지 못했다는 사실을 밝혀냈다. 형사는 이러한 증언을 바탕으로 사건의 퍼즐을 맞추었고, 피해자가 겪었던 고통과 그로 인한 심리적 압박이 어떻게 비극적인 결말로 이어졌는지를 완전히 이해하게 되었다. 결국, 형사는 친구의 증언을 통해 사건의 진실을 밝히며, 피해자의 고통이 어떻게 그의 생명을 앗아가는 결과로 이어졌는지를 명확히 파악했다.",
            image: "resources/img/choice2_3_2ding.jpg"
        }
    },
    choice2_3_3: {
        text: "피해자가 남긴 유언장을 조사한 강준호 형사는 그 유언장 속에 담긴 의도를 파악하기 시작한다. 유언장에는 피해자가 자신의 생명에 대한 불안을 느꼈음을 암시하는 문구들이 적혀 있었으며, 이는 그가 누군가에게 위협을 받고 있었음을 시사한다. 형사는 유언장의 내용과 그 배경을 분석하며, 피해자가 어떤 이유로 생명에 대한 불안을 느꼈는지를 깊이 탐구한다.",
        image: "resources/img/choice2_3_3.jpg",
        choices: [],
        ending: {
            text: "유언장은 피해자가 자신의 생명에 대한 불안을 느꼈음을 보여주었다. 그는 누군가에게 쫓기고 있었고, 결국 그것이 그의 죽음을 초래했다. 형사는 피해자의 마지막 메시지를 통해 사건의 전말을 밝혀냈다. 추가 조사 결과, 피해자는 직장 내 부정을 폭로하려던 중 위협을 받았으며, 이러한 위협이 그의 생명을 앗아가는 계기가 되었다는 사실을 확인했다. 형사는 유언장과 기타 증거들을 종합하여, 피해자가 받았던 위협의 실체와 그 배후에 있는 인물들을 밝혀냈다. 결국, 형사는 사건의 배후에 있는 조직이나 개인들을 추적하여 그들을 체포하며, 피해자의 고통과 희생이 헛되지 않도록 사건을 완전히 해결했다.",
            image: "resources/img/choice2_3_3ding.jpg"
        }
    },
    choice3: {
        text: "피해자와 가까운 사람들의 알리바이를 조사하기 시작한 강준호 형사는 피해자의 비서, 동료, 심지어 가족들까지도 모두 용의선상에 올랐음을 확인한다. 각 용의자들은 자신만의 이유로 사건과 연관이 있을 수 있으며, 형사는 이를 철저히 조사하여 진실을 밝히려 한다. 형사는 각 용의자들의 배경과 관계를 면밀히 분석하며, 그들의 동기와 알리바이를 검증하기 위해 다양한 조사를 진행한다.",
        image: "resources/img/choice3.jpg",
        choices: [
            { text: "피해자의 비서의 알리바이를 조사한다.", next: "choice3_1" },
            { text: "피해자의 가족들과의 관계를 파헤친다.", next: "choice3_2" },
            { text: "피해자의 동료들과의 관계를 조사한다.", next: "choice3_3" }
        ]
    },
    choice3_1: {
        text: "피해자의 비서가 사건 당일 어디에 있었는지를 조사한 강준호 형사는 비서가 사건 전날 밤까지 피해자와 함께 있었다고 증언하지만, 그 후의 행적이 명확하지 않았음을 발견했다. 비서의 알리바이가 완벽해 보였지만, 형사는 그 속에 숨겨진 허점을 찾아내려 한다. 형사는 비서의 스케줄과 통화 기록을 분석하며, 그가 실제로 어디에 있었는지를 파악하기 위해 노력한다.",
        image: "resources/img/choice3_1.jpg",
        choices: [
            { text: "비서의 통화 기록을 확인한다.", next: "choice3_1_1" },
            { text: "비서가 사건 당일 누구와 만났는지 조사한다.", next: "choice3_1_2" },
            { text: "비서의 사무실을 뒤져 추가 단서를 찾는다.", next: "choice3_1_3" }
        ]
    },
    choice3_1_1: {
        text: "비서의 통화 기록을 조사한 강준호 형사는 사건 당일 비서가 여러 사람과 통화한 사실을 확인했다. 특히, 피해자와의 마지막 통화에서 비서는 중요한 정보를 전달한 것으로 보인다. 형사는 이 통화의 내용을 상세히 분석하여, 비서가 전달한 정보가 사건 해결에 어떤 단서를 제공하는지를 파악하려 한다.",
        image: "resources/img/choice3_1_1.jpg",
        choices: [],
        ending: {
            text: "통화 기록은 비서가 사건 당일 중요한 정보를 피해자에게 전달했음을 보여주었다. 그는 피해자와의 마지막 통화에서 미심쩍은 말을 남겼고, 그것이 사건의 단서가 되었다. 형사는 비서의 말을 추적하며, 그가 전달한 정보의 정확성과 신뢰성을 검증했다. 추가 조사 결과, 비서는 피해자가 직면했던 문제와 관련된 중요한 사실을 숨기고 있었으며, 이는 사건의 배후에 있는 인물들과의 연결고리를 제공했다. 형사는 비서의 역할과 그가 왜 이러한 정보를 숨기려 했는지를 파악하며, 사건의 진실을 밝혀내는 데 결정적인 단서를 얻었다.",
            image: "resources/img/choice3_1_1ding.jpg"
        }
    },
    choice3_1_2: {
        text: "비서가 사건 당일 만났던 사람들을 추적한 강준호 형사는 그 중 한 명이 사건과 깊은 관련이 있음을 알게 된다. 이 사람은 피해자의 비서와 긴밀한 관계를 맺고 있었으며, 사건과 관련된 중요한 정보를 가지고 있었다. 형사는 이 인물과의 관계와 그가 사건과 어떤 식으로 연결되는지를 면밀히 조사하기 시작한다.",
        image: "resources/img/choice3_1_2.jpg",
        choices: [],
        ending: {
            text: "비서가 만난 사람은 피해자의 적이었다. 그 만남은 단순한 업무적 만남이 아닌, 사건의 중심에 있었던 비밀 회동이었다. 형사는 그날의 만남이 사건의 열쇠였음을 깨닫고 사건을 해결했다. 이 비밀 회동에서 범인은 피해자에게 위협을 가하거나 중요한 정보를 전달했을 가능성이 높았으며, 형사는 이를 통해 범인의 동기와 목적을 명확히 이해하게 되었다. 결국, 이 인물의 증언과 추가 증거들을 바탕으로 사건의 진실을 파악하고, 범인을 체포함으로써 사건을 성공적으로 해결하게 되었다.",
            image: "resources/img/choice3_1_2ding.jpg"
        }
    },
    choice3_1_3: {
        text: "비서의 사무실을 수색한 강준호 형사는 피해자와 비서 사이에 오간 비밀스러운 문서들을 발견했다. 이 문서들은 회사의 내부 비밀과 관련된 중요한 정보들을 담고 있었으며, 피해자가 이를 통해 어떤 목적으로 조사하고 있었는지를 알 수 있었다. 형사는 이 문서들을 분석하여, 그 내용이 사건 해결에 어떤 영향을 미치는지를 파악하려 한다.",
        image: "resources/img/choice3_1_3.jpg",
        choices: [],
        ending: {
            text: "문서에는 피해자의 마지막 행적과 그가 조사하던 비밀들이 기록되어 있었다. 비서는 그 문서를 통해 피해자가 얼마나 큰 위험에 처해 있었는지 알고 있었고, 그 사실이 사건을 해결하는 데 중요한 단서가 되었다. 형사는 이 단서를 바탕으로 피해자가 조사하던 내부 비리와 관련된 인물들을 추적했으며, 결국 이로 인해 주요 용의자를 특정하고 그를 체포하는 데 성공했다. 비서의 문서들은 피해자가 직면했던 위협의 실체를 밝히는 데 결정적인 역할을 했으며, 형사는 이를 통해 사건의 전모를 완전히 파악하고 종결지을 수 있었다.",
            image: "resources/img/choice3_1_3ding.jpg"
        }
    },
    choice3_2: {
        text: "피해자의 가족들과의 관계를 파헤친 강준호 형사는 가족들 사이에 오랜 기간 동안 지속된 갈등과 특히 경제적인 문제로 인해 큰 불화가 있었다는 사실을 알게 되었다. 가족 간의 재산 분쟁과 서로에 대한 불신이 사건과 어떻게 연결되어 있는지를 조사하기 시작한다. 형사는 가족 구성원들의 재산 상황과 그들이 사건에 어떻게 연루될 수 있는지를 면밀히 분석하며, 각자의 동기와 알리바이를 검증하기 위해 다양한 조사를 진행한다.",
        image: "resources/img/choice3_2.jpg",
        choices: [
            { text: "가족들의 재산 문제를 추적해 본다.", next: "choice3_2_1" },
            { text: "가족의 알리바이를 조사해 본다.", next: "choice3_2_2" },
            { text: "피해자의 가족 회동에 참석해 비밀스러운 대화를 듣는다.", next: "choice3_2_3" }
        ]
    },
    choice3_2_1: {
        text: "가족들의 재산 문제를 조사한 강준호 형사는 그들이 피해자의 재산을 둘러싸고 치열한 분쟁을 벌이고 있었다는 사실을 발견했다. 피해자는 최근 재산 분배와 관련된 중요한 결정을 내려야 했으며, 이는 가족 간의 갈등을 더욱 심화시켰다. 형사는 재산 분배 계획서와 관련된 금융 기록을 분석하며, 가족 구성원들이 사건에 어떻게 연루될 수 있는지를 파악하려 한다.",
        image: "resources/img/choice3_2_1.jpg",
        choices: [],
        ending: {
            text: "가족들은 피해자의 재산을 두고 분쟁을 벌이고 있었고, 그 갈등이 결국 사건으로 이어졌다. 경제적 이익을 둘러싼 가족 간의 다툼은 결국 한 사람의 목숨을 앗아가게 만들었다. 형사는 재산 분쟁의 내막을 파헤치며, 누가 피해자의 재산을 갈망했는지를 명확히 밝혀냈다. 추가적인 증거를 통해, 특정 가족 구성원이 피해자의 재산을 얻기 위해 범행을 저질렀다는 것을 입증했으며, 그로 인해 해당 가족 구성원을 체포하고 사건을 종결지었다.",
            image: "resources/img/choice3_2_1ding.jpg"
        }
    },
    choice3_2_2: {
        text: "가족의 알리바이를 조사한 강준호 형사는 사건 당일 가족들이 어디에 있었는지를 확인하기 시작했다. 각 가족 구성원들은 자신들의 알리바이를 제시했지만, 그 중 한 명의 알리바이에 의심스러운 점이 발견되었다. 형사는 그 가족 구성원의 알리바이를 재검토하며, 추가적인 증거를 통해 그의 진실성을 검증하려 한다.",
        image: "resources/img/choice3_2_2.jpg",
        choices: [],
        ending: {
            text: "조사 결과, 가족 중 한 명이 사건 당일 피해자와의 심한 언쟁 끝에 마지막으로 그를 본 것으로 밝혀졌다. 그 날의 다툼이 사건의 발단이 되었고, 가족은 결국 죄책감을 견디지 못하고 진실을 고백했다. 형사는 추가적인 증거를 확보하여, 해당 가족 구성원이 사건을 일으킨 주범임을 확증했다. 그의 자백과 함께, 사건은 명확히 밝혀졌으며, 형사는 피해자의 가족이 겪었던 깊은 갈등이 어떻게 비극적인 결과로 이어졌는지를 완전히 이해하게 되었다.",
            image: "resources/img/choice3_2_2ding.jpg"
        }
    },
    choice3_2_3: {
        text: "피해자의 가족 회동에 참석한 강준호 형사는 그 자리에서 가족들 간의 비밀스러운 대화를 엿듣게 되었다. 대화 속에서는 가족 간의 깊은 불신과 갈등이 드러났으며, 이는 사건과 밀접한 관련이 있음을 시사했다. 형사는 이러한 대화를 통해 가족 구성원들이 서로에 대해 가지고 있는 진정한 감정과 숨겨진 비밀을 파악하려 한다.",
        image: "resources/img/choice3_2_3.jpg",
        choices: [],
        ending: {
            text: "가족 회동에서 밝혀진 비밀스러운 대화는 사건의 전말을 여실히 드러냈다. 가족 간의 분쟁과 갈등은 예상보다 깊었고, 그 불화는 결국 비극적인 결말을 초래했다. 형사는 대화 속에서 가족 구성원들이 서로를 어떻게 적대시하고 있었는지를 명확히 이해하게 되었다. 추가적인 증거와 증언을 통해, 특정 가족 구성원이 사건을 일으킨 주범임을 입증했으며, 그들의 복잡한 감정과 갈등이 어떻게 사건으로 이어졌는지를 완전히 파악했다. 형사는 이를 통해 사건을 성공적으로 해결하며, 피해자의 고통과 가족 간의 비극을 종결지었다.",
            image: "resources/img/choice3_2_3ding.jpg"
        }
    },
    choice3_3: {
        text: "피해자의 동료들과의 관계를 조사한 강준호 형사는 피해자가 직장에서 경쟁 관계에 있었던 동료들과의 갈등이 심했다는 사실을 알게 되었다. 이 갈등은 업무상의 경쟁에서 비롯된 것으로, 피해자는 동료들과의 갈등을 해결하려 애쓰고 있었지만, 상황은 점점 악화되고 있었다. 형사는 피해자가 직면했던 구체적인 갈등 상황과 그로 인해 발생한 문제점을 파악하기 위해, 동료들의 증언과 피해자의 업무 기록을 면밀히 조사하기 시작한다.",
        image: "resources/img/choice3_3.jpg",
        choices: [
            { text: "동료의 알리바이를 확인한다.", next: "choice3_3_1" },
            { text: "피해자가 마지막으로 다룬 사건에서의 동료와의 충돌을 조사한다.", next: "choice3_3_2" },
            { text: "피해자의 사무실을 조사해 동료들과의 교류 흔적을 찾는다.", next: "choice3_3_3" }
        ]
    },
    choice3_3_1: {
        text: "피해자의 동료 중 한 명의 알리바이를 확인한 강준호 형사는 그 동료가 사건 당일 예상치 못한 행동을 했다는 사실을 발견했다. 동료는 피해자와의 만남이 있었음에도 불구하고, 자신의 알리바이를 완벽하게 제시하려 했다는 점에서 의심을 가지게 되었다. 형사는 이 동료의 알리바이를 더욱 철저히 검증하기 위해, 그의 이동 경로와 관련된 추가적인 증거를 수집하기 시작한다.",
        image: "resources/img/choice3_3_1.jpg",
        choices: [],
        ending: {
            text: "조사 끝에 동료의 알리바이는 거짓으로 판명되었다. 그는 사건 당일 피해자와 만났고, 그 만남이 사건의 발단이 되었다. 형사는 동료가 피해자와의 갈등을 해결하려다 범행을 저질렀음을 확신하게 되었으며, 그의 알리바이 조작의 동기와 방법을 철저히 파악했다. 동료는 결국 자신의 실수를 인정하며 사건의 책임을 받아들였고, 형사는 이로써 사건을 종결지었다. 피해자와의 갈등이 어떻게 비극적인 결과로 이어졌는지를 명확히 이해한 형사는, 인간 관계의 복잡성과 감정의 중요성을 다시 한번 깊이 인식하게 되었다.",
            image: "resources/img/choice3_3_1ding.jpg"
        }
    },
    choice3_3_2: {
        text: "피해자가 마지막으로 다룬 사건에서 동료와의 충돌을 조사한 강준호 형사는 그 충돌이 사건의 원인이 되었음을 확인했다. 동료와의 갈등은 단순한 업무상의 문제가 아니라, 개인적인 감정이 섞여 있었음이 드러났다. 형사는 동료와 피해자 간의 과거 관계와 그들이 직면했던 구체적인 갈등 상황을 파악하기 위해, 두 사람의 과거 업무 기록과 개인적인 관계를 면밀히 조사하기 시작한다.",
        image: "resources/img/choice3_3_2.jpg",
        choices: [],
        ending: {
            text: "피해자가 다루던 사건은 동료와의 충돌로 이어졌고, 그 갈등은 결국 사건의 원인이 되었다. 동료는 피해자가 자신의 커리어에 위협이 된다고 느꼈고, 그 감정이 결국 비극으로 번졌다. 형사는 동료와 피해자 간의 심리적 갈등과 그들이 직면했던 구체적인 상황들을 철저히 분석하여, 동료가 범행을 저질렀다는 확실한 증거를 확보했다. 동료는 자신의 행동에 대한 깊은 후회와 함께, 피해자와의 갈등이 어떻게 비극적인 결과로 이어졌는지를 설명하며 자신의 죄를 인정했다. 형사는 이로써 사건의 진실을 완전히 파악하고, 피해자와 동료 간의 갈등이 어떻게 비극으로 귀결되었는지를 명확히 이해하게 되었다.",
            image: "resources/img/choice3_3_2ding.jpg"
        }
    },
    choice3_3_3: {
        text: "피해자의 사무실을 조사한 강준호 형사는 동료들과 주고받은 문서와 메시지들을 찾아냈다. 이 문서들은 두 사람 간의 복잡한 관계와 숨겨진 갈등을 드러냈으며, 이는 사건의 도화선이 되었다는 단서를 제공했다. 형사는 이 문서들을 분석하여, 두 사람 간의 갈등이 어떻게 비극적인 결과로 이어졌는지를 파악하려 한다.",
        image: "resources/img/choice3_3_3.jpg",
        choices: [],
        ending: {
            text: "문서와 메시지 속에는 동료와 피해자의 복잡한 관계와 숨겨진 갈등이 담겨 있었다. 두 사람의 관계는 예상보다 깊고 치열했으며, 그 갈등이 사건의 도화선이 되었다. 형사는 두 사람 간의 갈등이 어떻게 비극적인 결과로 이어졌는지를 상세히 분석하여, 동료가 피해자를 범행하게 된 동기와 방법을 명확히 이해하게 되었다. 추가적인 증거와 증언을 통해, 형사는 동료가 피해자를 제압하려던 중 우발적인 사고로 인해 사건이 발생했음을 입증했다. 결국, 형사는 동료의 자백과 증거들을 통해 사건의 전모를 밝히며, 피해자의 비극적인 죽음을 종결지었다. 형사는 이 사건을 통해 인간 관계의 복잡성과 감정의 중요성을 다시 한번 깨닫게 되었으며, 사건 해결 과정에서의 세심한 조사와 분석의 중요성을 깊이 인식하게 되었다.",
            image: "resources/img/choice3_3_3_ending.jpg"
    	}
      }
   }
};


// 클릭 시 사운드 재생
function playClickSound() {
    const clickSound = document.getElementById("clickSound");
    if (clickSound) {
        clickSound.play();
    }
}

window.onload = function() {
    document.addEventListener("click", playClickSound);
    addSkipButton(); // Skip 버튼 추가

    // 항상 'start'부터 시작
    const titleContainer = document.getElementById("title-container");
    titleContainer.classList.remove("hidden");
    const storyContainer = document.getElementById("story-container");
    storyContainer.classList.add("hidden");
    
    // 브라우저 언어 감지 후 언어 변경
    const userLang = navigator.language || navigator.userLanguage;
    if (userLang.startsWith('en')) {
        changeLanguage('en');
    } else {
        changeLanguage('ko');
    }
};

let currentLanguage = 'ko'; // 기본 언어 설정

// 언어 변경 함수
function changeLanguage(lang) {
    currentLanguage = lang;
    // 현재 스토리가 표시되고 있다면 다시 표시
    const storyContainer = document.getElementById("story-container");
    if (!storyContainer.classList.contains("hidden")) {
        displayStory(currentStoryPart);
    } else {
        // 제목 및 시작 버튼 업데이트
        updateTitle();
    }
}

let currentStoryPart = 'start'; // 현재 스토리 부분 추적

// 시작 함수 수정
function startStory() {
    currentStoryPart = 'start';
    // 제목 및 시작 버튼 숨기기
    const titleContainer = document.getElementById("title-container");
    titleContainer.classList.add("hidden");

    // 스토리 컨테이너 보이기
    const storyContainer = document.getElementById("story-container");
    storyContainer.classList.remove("hidden");

    // 스토리 시작
    displayStory(currentStoryPart);
}

// 제목 업데이트 함수 추가
function updateTitle() {
    const storyTitle = document.getElementById("story-title");
    const storyDescription = document.querySelector(".story-text");
    if (currentLanguage === 'en') {
        storyTitle.textContent = "Perfect Crime";
        storyDescription.textContent = "Detective Kang Junho, a seasoned officer, quickly moves to the scene of a meticulously planned murder case to track down clues. Initially, the crime scene seemed flawless, but he discovers hidden truths and twists.";
    } else if (currentLanguage === 'ko') {
        storyTitle.textContent = "완벽한 범죄";
        storyDescription.textContent = "강준호 형사는 베테랑으로, 완벽하게 계획된 살인사건 현장에서 단서를 추적해 나간다. 처음엔 완벽해 보였던 범죄 속에서 그는 숨겨진 진실과 반전을 발견하게 된다.";
    }
    // 기타 언어 추가 시 이곳에 추가
}

// 스토리 표시 함수 수정
function displayStory(part) {
    const story = storyData[currentLanguage][part];
    const storyContainer = document.getElementById("story-container");
    const storyContent = document.getElementById("story-content");

    // 배경 이미지 설정
    storyContainer.style.backgroundImage = `url('${contextPath}/${story.image}')`;

    // 기존 내용 초기화 및 텍스트만 추가
    storyContent.innerHTML = `<p class="story-text"></p>`;

    const storyText = storyContent.querySelector(".story-text");

    let textIndex = 0;
    const textSpeed = 30; // 텍스트 타이핑 속도를 조정 (밀리초 단위)
    const text = story.text;

    let isSkipping = false; // 스킵 여부를 확인하는 변수 초기화

    // 기존에 등록된 이벤트 리스너 제거
    storyContainer.removeEventListener('click', skipTyping);

    // 클릭 시 전체 텍스트 표시
    function skipTyping() {
        isSkipping = true;
    }

    // 클릭 이벤트 리스너 추가
    storyContainer.addEventListener('click', skipTyping);

    // 타이핑 효과 함수
    function typeWriter() {
        if (isSkipping) {
            // 남은 텍스트를 한꺼번에 출력
            storyText.innerHTML = text;
            // 클릭 이벤트 리스너 제거
            storyContainer.removeEventListener('click', skipTyping);
            // 선택지 표시
            displayChoices(story);
        } else if (textIndex < text.length) {
            storyText.innerHTML += text.charAt(textIndex);
            textIndex++;
            setTimeout(typeWriter, textSpeed);
        } else {
            // 클릭 이벤트 리스너 제거
            storyContainer.removeEventListener('click', skipTyping);
            // 선택지 표시
            displayChoices(story);
        }
    }

    currentStoryPart = part; // 현재 스토리 부분 업데이트
    typeWriter();

    // TTS 중지
    if (isTTSActive) {
        synth.cancel();
        isTTSActive = false;
        updateTTSButton(false);
    }
}

// 선택지 버튼 표시 함수
function displayChoices(story) {
    const storyContent = document.getElementById("story-content");

    // 선택지가 있을 때 버튼 생성
    if (story.choices && story.choices.length > 0) {
        story.choices.forEach(choice => {
            const button = document.createElement("button");
            button.textContent = choice.text;
            button.className = "choice"; // 버튼 스타일
            button.onclick = () => displayStory(choice.next);
            storyContent.appendChild(button);
        });
    } else if (story.ending) {
        // 엔딩으로 가는 버튼 생성
        const endingButton = document.createElement("button");
        endingButton.textContent = "엔딩으로";
        endingButton.className = "next-button"; // 버튼 스타일 (원하는 클래스 사용)
        endingButton.onclick = () => showEnding(story);
        storyContent.appendChild(endingButton);
    }
}

let isTTSActive = false;
let synth = window.speechSynthesis;
let utterance = null;

// TTS 토글 함수
function toggleTTS() {
    if (!synth) {
        alert("이 브라우저는 음성 합성을 지원하지 않습니다.");
        return;
    }

    if (isTTSActive) {
        synth.cancel();
        isTTSActive = false;
        updateTTSButton(false);
    } else {
        const story = storyData[currentLanguage][currentStoryPart];
        if (utterance) {
            synth.cancel();
        }
        utterance = new SpeechSynthesisUtterance(story.text);
        // 언어 설정
        utterance.lang = currentLanguage === 'en' ? 'en-US' : 'ko-KR';
        synth.speak(utterance);
        isTTSActive = true;
        updateTTSButton(true);
        // 음성 합성이 끝났을 때 상태 업데이트
        utterance.onend = function() {
            isTTSActive = false;
            updateTTSButton(false);
        };
    }
}

// TTS 버튼 상태 업데이트
function updateTTSButton(active) {
    const ttsButton = document.querySelector(".tts-button");
    if (active) {
        ttsButton.innerHTML = `<i class="fas fa-volume-mute"></i> 음성 중지`;
    } else {
        ttsButton.innerHTML = `<i class="fas fa-volume-up"></i> 음성 읽기`;
    }
}

// 엔딩 표시 함수
function showEnding(story) {
    const storyContainer = document.getElementById("story-container");
    const storyContent = document.getElementById("story-content");

    // 엔딩 배경 이미지 설정
    storyContainer.style.backgroundImage = `url('${contextPath}/${story.ending.image}')`;

    // 기존 내용 초기화
    storyContent.innerHTML = `
        <p class="ending">${story.ending.text}</p>
        <button class="main-button">
            <i class="fas fa-home"></i> 메인으로
        </button>
    `;

    // '메인으로' 버튼 클릭 시 동작
    const mainButton = storyContent.querySelector(".main-button");
    mainButton.onclick = () => {
        window.location.href = `${contextPath}/main`; // 메인 페이지로 이동
    };
}

function addSkipButton() {
    const skipButton = document.createElement("button");
    skipButton.innerHTML = `<i class="fas fa-forward"></i> Skip`;
    skipButton.className = "skip-button";
    skipButton.onclick = () => {
        if (confirm("메인스토리를 스킵 하시겠습니까?")) {
        	const token = localStorage.getItem('token');
            if (!token) {
                alert('로그인을 먼저 진행해주세요.');
                window.location.href = `${contextPath}/login`;
                return;
            }

            fetch(`${contextPath}/api/skip-main-story`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            })
            .then(response => {
                if (response.ok) {
                    window.location.href = 'main'; // main.jsp로 이동
                } else {
                    alert('메인 스토리 스킵에 실패했습니다. 다시 시도해주세요.');
                }
            })
            .catch(error => {
                console.error('메인 스토리 스킵 중 오류:', error);
            });
        }
    };

    // Skip 버튼 추가된 스타일을 이미 CSS에서 처리하므로 여기서는 그냥 추가
    document.body.appendChild(skipButton);
}
