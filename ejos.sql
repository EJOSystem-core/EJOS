--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.5
-- Dumped by pg_dump version 9.6.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: achievements; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE achievements (
    id integer NOT NULL,
    answer character varying(10000),
    answer_path character varying(255),
    result character varying(2000),
    score integer,
    question_id integer,
    student_id integer
);


ALTER TABLE achievements OWNER TO postgres;

--
-- Name: admin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE admin (
    id integer NOT NULL,
    email character varying(64),
    name character varying(64),
    password character varying(32)
);


ALTER TABLE admin OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- Name: news; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE news (
    id integer NOT NULL,
    content character varying(255),
    endtime timestamp without time zone,
    level character varying(255),
    starttime timestamp without time zone,
    status character varying(32),
    title character varying(64),
    student_id integer
);


ALTER TABLE news OWNER TO postgres;

--
-- Name: question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE question (
    id integer NOT NULL,
    answer character varying(10000),
    deadline timestamp without time zone,
    diffculty integer,
    introduce character varying(255),
    path character varying(255),
    question character varying(255),
    result character varying(2000),
    "time" timestamp without time zone,
    team_id integer
);


ALTER TABLE question OWNER TO postgres;

--
-- Name: record; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE record (
    id integer NOT NULL,
    question_id integer,
    status character varying(255),
    student_id integer,
    "time" timestamp without time zone
);


ALTER TABLE record OWNER TO postgres;

--
-- Name: student; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE student (
    id integer NOT NULL,
    id_card character varying(32),
    name character varying(64),
    password character varying(32),
    student_num bigint,
    team_id integer
);


ALTER TABLE student OWNER TO postgres;

--
-- Name: teacher; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE teacher (
    id integer NOT NULL,
    id_card character varying(32),
    name character varying(64),
    password character varying(64),
    teacher_num bigint,
    tel bigint
);


ALTER TABLE teacher OWNER TO postgres;

--
-- Name: team; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE team (
    id integer NOT NULL,
    introduce character varying(64),
    name character varying(64),
    teacher_id integer
);


ALTER TABLE team OWNER TO postgres;

--
-- Data for Name: achievements; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY achievements (id, answer, answer_path, result, score, question_id, student_id) FROM stdin;
\.


--
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY admin (id, email, name, password) FROM stdin;
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 201, true);


--
-- Data for Name: news; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY news (id, content, endtime, level, starttime, status, title, student_id) FROM stdin;
\.


--
-- Data for Name: question; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY question (id, answer, deadline, diffculty, introduce, path, question, result, "time", team_id) FROM stdin;
2	public class U{ public String name;}	\N	22	设计一个应用程序，原始数据从程序界面的一个文本区输入，用户点击按钮后，在另一个文本区上输出排序后的数据，并将排序后的数\n\n　　据输出到文件中。已给出部分代码，请完成程序	/path/home	Swing设计题	a=1	\N	2
4	int[] fun(int[]a)\n\n　　{\n\n　　int []b=new int[a.length];\n\n　　int i,j,k=0;\n\n　　b[k]=a[0];\n\n　　for(i=1;i<a.length;i++)\n\n　　{\n\n　　for(j=0;j<=k;j++)\n\n　　if (a[i]==b[j])break;\n\n　　if (j>k)\n\n　　{\n\n　　k++;\n\n　　b[k]=a[i];\n\n　　}\n\n　　}\n\n　　int []c=new int[k+1];\n\n　　for(i=0;i<=k;i++)c[i]=b[i];\n\n　　return c;\n\n　　}	\N	44	编写一个方法fun()，要求该方法有一个元素类型为整型的数组参数，方法的功能是把参数数组中元素值相同的元素删成只剩一个，经\n\n　　过删除后会得到一个新数组，方法返回这个新数组。 (6分)	/path/home/eeqrq	数组操作题	a=2	\N	1
3	public class U{ public String name;}	\N	33	设计一个应用程序，原始数据从程序界面的一个文本区输入，用户点击按钮后，在另一个文本区上输出排序后的数据，并将排序后的数\n\n　　据输出到文件中。已给出部分代码，请完成程序	/path/home/me	Swing设计题	a=1	\N	3
6	int[] fun(int[]a)\n\n　　{\n\n　　int []b=new int[a.length];\n\n　　int i,j,k=0;\n\n　　b[k]=a[0];\n\n　　for(i=1;i<a.length;i++)\n\n　　{\n\n　　for(j=0;j<=k;j++)\n\n　　if (a[i]==b[j])break;\n\n　　if (j>k)\n\n　　{\n\n　　k++;\n\n　　b[k]=a[i];\n\n　　}\n\n　　}\n\n　　int []c=new int[k+1];\n\n　　for(i=0;i<=k;i++)c[i]=b[i];\n\n　　return c;\n\n　　}	\N	66	编写一个方法fun()，要求该方法有一个元素类型为整型的数组参数，方法的功能是把参数数组中元素值相同的元素删成只剩一个，经\n\n　　过删除后会得到一个新数组，方法返回这个新数组。 (6分)	/path/home/me/data	数组操作题	a=2	\N	3
5	int[] fun(int[]a)\n\n　　{\n\n　　int []b=new int[a.length];\n\n　　int i,j,k=0;\n\n　　b[k]=a[0];\n\n　　for(i=1;i<a.length;i++)\n\n　　{\n\n　　for(j=0;j<=k;j++)\n\n　　if (a[i]==b[j])break;\n\n　　if (j>k)\n\n　　{\n\n　　k++;\n\n　　b[k]=a[i];\n\n　　}\n\n　　}\n\n　　int []c=new int[k+1];\n\n　　for(i=0;i<=k;i++)c[i]=b[i];\n\n　　return c;\n\n　　}	\N	55	编写一个方法fun()，要求该方法有一个元素类型为整型的数组参数，方法的功能是把参数数组中元素值相同的元素删成只剩一个，经\n\n　　过删除后会得到一个新数组，方法返回这个新数组。 (6分)	/path/home/ejosData	数组操作题	a=2	\N	2
1	public class U{ public String name;}	\N	11	设计一个应用程序，原始数据从程序界面的一个文本区输入，用户点击按钮后，在另一个文本区上输出排序后的数据，并将排序后的数\n\n　　据输出到文件中。已给出部分代码，请完成程序	/path1	Swing设计题	a=1	\N	1
8	int[] fun(int[]a)\n\n　　{\n\n　　int []b=new int[a.length];\n\n　　int i,j,k=0;\n\n　　b[k]=a[0];\n\n　　for(i=1;i<a.length;i++)\n\n　　{\n\n　　for(j=0;j<=k;j++)\n\n　　if (a[i]==b[j])break;\n\n　　if (j>k)\n\n　　{\n\n　　k++;\n\n　　b[k]=a[i];\n\n　　}\n\n　　}\n\n　　int []c=new int[k+1];\n\n　　for(i=0;i<=k;i++)c[i]=b[i];\n\n　　return c;\n\n　　}	\N	88	编写一个方法fun()，要求该方法有一个元素类型为整型的数组参数，方法的功能是把参数数组中元素值相同的元素删成只剩一个，经\n\n　　过删除后会得到一个新数组，方法返回这个新数组。 (6分)	/path/home/me/data	数组操作题	a=2	\N	5
7	int[] fun(int[]a)\n\n　　{\n\n　　int []b=new int[a.length];\n\n　　int i,j,k=0;\n\n　　b[k]=a[0];\n\n　　for(i=1;i<a.length;i++)\n\n　　{\n\n　　for(j=0;j<=k;j++)\n\n　　if (a[i]==b[j])break;\n\n　　if (j>k)\n\n　　{\n\n　　k++;\n\n　　b[k]=a[i];\n\n　　}\n\n　　}\n\n　　int []c=new int[k+1];\n\n　　for(i=0;i<=k;i++)c[i]=b[i];\n\n　　return c;\n\n　　}	\N	77	编写一个方法fun()，要求该方法有一个元素类型为整型的数组参数，方法的功能是把参数数组中元素值相同的元素删成只剩一个，经\n\n　　过删除后会得到一个新数组，方法返回这个新数组。 (6分)	/path/home/ejos	数组操作题	a=2	\N	4
9	int[] fun(int[]a)\n\n　　{\n\n　　int []b=new int[a.length];\n\n　　int i,j,k=0;\n\n　　b[k]=a[0];\n\n　　for(i=1;i<a.length;i++)\n\n　　{\n\n　　for(j=0;j<=k;j++)\n\n　　if (a[i]==b[j])break;\n\n　　if (j>k)\n\n　　{\n\n　　k++;\n\n　　b[k]=a[i];\n\n　　}\n\n　　}\n\n　　int []c=new int[k+1];\n\n　　for(i=0;i<=k;i++)c[i]=b[i];\n\n　　return c;\n\n　　}	\N	99	编写一个方法fun()，要求该方法有一个元素类型为整型的数组参数，方法的功能是把参数数组中元素值相同的元素删成只剩一个，经\n\n　　过删除后会得到一个新数组，方法返回这个新数组。 (6分)	/path/home/data	数组操作题	a=2	\N	6
\.


--
-- Data for Name: record; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY record (id, question_id, status, student_id, "time") FROM stdin;
\.


--
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY student (id, id_card, name, password, student_num, team_id) FROM stdin;
11	370101199902031211	方十一	123456	15110471011	5
2	370101199902031202	刘二狗	123456	15110471002	2
1	370101199902031201	张一曼	123456	15110471001	1
4	370101199902031204	王四喜	123456	15110471004	1
3	370101199902031203	王川三	123456	15110471003	3
6	370101199902031206	李小六	123456	15110471006	3
5	370101199902031205	张小五	123456	15110471005	2
8	370101199902031208	胡八一	123456	15110471008	2
7	370101199902031207	宋七七	123456	15110471007	1
10	370101199902031210	曾十法	123456	15110471010	4
9	370101199902031209	张玖	123456	15110471009	3
\.


--
-- Data for Name: teacher; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY teacher (id, id_card, name, password, teacher_num, tel) FROM stdin;
2	370105197708081234	王跃进	123456	100002	13726458966
1	370105197001051234	张建国	123456	100001	13506423686
3	370105197708089999	苗翠兰	123456	100003	13726458967
\.


--
-- Data for Name: team; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY team (id, introduce, name, teacher_id) FROM stdin;
2	成绩优异	电课本1502	2
1	团结,友爱	电课本1501	1
3	积极向上	电课本1503	3
4	团结	电课本1504	1
6	氛围差	电课本1506	3
5	积极	电课本1505	2
\.


--
-- Name: achievements achievements_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY achievements
    ADD CONSTRAINT achievements_pkey PRIMARY KEY (id);


--
-- Name: admin admin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (id);


--
-- Name: news news_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY news
    ADD CONSTRAINT news_pkey PRIMARY KEY (id);


--
-- Name: question question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY question
    ADD CONSTRAINT question_pkey PRIMARY KEY (id);


--
-- Name: record record_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY record
    ADD CONSTRAINT record_pkey PRIMARY KEY (id);


--
-- Name: student student_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY student
    ADD CONSTRAINT student_pkey PRIMARY KEY (id);


--
-- Name: teacher teacher_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY teacher
    ADD CONSTRAINT teacher_pkey PRIMARY KEY (id);


--
-- Name: team team_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY team
    ADD CONSTRAINT team_pkey PRIMARY KEY (id);


--
-- Name: achievements fk14tlxomruhth46m2u50u0e2uq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY achievements
    ADD CONSTRAINT fk14tlxomruhth46m2u50u0e2uq FOREIGN KEY (student_id) REFERENCES student(id);


--
-- Name: question fk19lx7nc9l9ltceqd3aentgvvo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY question
    ADD CONSTRAINT fk19lx7nc9l9ltceqd3aentgvvo FOREIGN KEY (team_id) REFERENCES team(id);


--
-- Name: achievements fk6k0x2675qwm3vxjtyfpq85yi1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY achievements
    ADD CONSTRAINT fk6k0x2675qwm3vxjtyfpq85yi1 FOREIGN KEY (question_id) REFERENCES question(id);


--
-- Name: team fkkxv512tau8qyw6s40efuhqq3w; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY team
    ADD CONSTRAINT fkkxv512tau8qyw6s40efuhqq3w FOREIGN KEY (teacher_id) REFERENCES teacher(id);


--
-- Name: news fkl69cvb779aw5372gaaok5tesp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY news
    ADD CONSTRAINT fkl69cvb779aw5372gaaok5tesp FOREIGN KEY (student_id) REFERENCES student(id);


--
-- Name: student fkmdgbo3apnk7o38pp6ua3ig139; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY student
    ADD CONSTRAINT fkmdgbo3apnk7o38pp6ua3ig139 FOREIGN KEY (team_id) REFERENCES team(id);


--
-- PostgreSQL database dump complete
--

