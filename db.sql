CREATE TABLE users (
    user_id integer NOT NULL,
    user_fname character varying(50),
    user_lname character varying(50),
    user_email character varying(100),
    user_password character varying(20),
    user_location character varying(50),
    user_date timestamp without time zone DEFAULT now()
);

ALTER TABLE public.users OWNER TO mcicflyvfampsu;

CREATE SEQUENCE users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_user_id_seq OWNER TO mcicflyvfampsu;


ALTER SEQUENCE users_user_id_seq OWNED BY users.user_id;


ALTER TABLE ONLY users ALTER COLUMN user_id SET DEFAULT nextval('users_user_id_seq'::regclass);


ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);
    
    
    
    
CREATE TABLE events (
    event_id integer NOT NULL,
    event_name character varying(100),
    event_location character varying(100),
    event_start_date character varying(20),
    event_end_date character varying(20),
    event_description text,
    event_image character varying(50),
    event_date timestamp without time zone DEFAULT now(),
    event_user_id integer NOT NULL
);


ALTER TABLE public.events OWNER TO mcicflyvfampsu;

CREATE SEQUENCE events_event_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.events_event_id_seq OWNER TO mcicflyvfampsu;

ALTER SEQUENCE events_event_id_seq OWNED BY events.event_id;

ALTER TABLE ONLY events ALTER COLUMN event_id SET DEFAULT nextval('events_event_id_seq'::regclass);

ALTER TABLE ONLY events
    ADD CONSTRAINT events_pkey PRIMARY KEY (event_id);

ALTER TABLE ONLY events
    ADD CONSTRAINT events_event_user_id_fkey FOREIGN KEY (event_user_id) REFERENCES users(user_id);




CREATE TABLE invited_users (
    iu_id integer NOT NULL,
    iu_from_id integer NOT NULL,
    iu_to_id integer NOT NULL,
    iu_event_id integer NOT NULL,
    iu_state character varying(10),
    iu_date timestamp without time zone DEFAULT now()
);


ALTER TABLE public.invited_users OWNER TO mcicflyvfampsu;

CREATE SEQUENCE invited_users_iu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.invited_users_iu_id_seq OWNER TO mcicflyvfampsu;

ALTER SEQUENCE invited_users_iu_id_seq OWNED BY invited_users.iu_id;

ALTER TABLE ONLY invited_users ALTER COLUMN iu_id SET DEFAULT nextval('invited_users_iu_id_seq'::regclass);

ALTER TABLE ONLY invited_users
    ADD CONSTRAINT invited_users_pkey PRIMARY KEY (iu_id);

ALTER TABLE ONLY invited_users
    ADD CONSTRAINT invited_users_iu_event_id_fkey FOREIGN KEY (iu_event_id) REFERENCES events(event_id);

ALTER TABLE ONLY invited_users
    ADD CONSTRAINT invited_users_iu_to_id_fkey FOREIGN KEY (iu_to_id) REFERENCES users(user_id);

ALTER TABLE ONLY invited_users
    ADD CONSTRAINT invited_users_iu_from_id_fkey FOREIGN KEY (iu_from_id) REFERENCES users(user_id);
