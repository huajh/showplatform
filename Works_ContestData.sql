USE [Works_ContestData]
GO
/****** Object:  Table [dbo].[Administor]    Script Date: 11/28/2011 06:47:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Administor](
	[Account] [char](20) NOT NULL,
	[Pwd] [char](20) NOT NULL,
	[Sex] [char](4) NULL,
	[Age] [int] NULL,
	[RealName] [char](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[Account] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Member]    Script Date: 11/28/2011 06:47:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Member](
	[MemberID] [char](20) NOT NULL,
	[Pwd] [char](20) NOT NULL,
	[Sex] [char](4) NULL,
	[Age] [int] NULL,
	[RealName] [char](30) NULL,
	[Birthday] [datetime] NULL,
	[CertifiedStatus] [int] NULL,
	[MemberPath] [nvarchar](100) NULL,
	[Credit] [int] NULL,
	[Constellation] [char](20) NULL,
	[RegisterTime] [datetime] NULL,
	[LastVisitTime] [datetime] NULL,
	[RegisterEmail] [char](50) NULL,
	[LiveProvince] [char](30) NULL,
	[LiveCity] [char](30) NULL,
	[GraduateSchool] [char](40) NULL,
	[Profession] [char](30) NULL,
	[Introduction] [text] NULL,
	[msg] [text] NULL,
PRIMARY KEY CLUSTERED 
(
	[MemberID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Contest]    Script Date: 11/28/2011 06:47:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Contest](
	[ContestID] [int] IDENTITY(1,1) NOT NULL,
	[Title] [char](40) NULL,
	[ContestType] [char](20) NULL,
	[WorksType] [char](20) NULL,
	[ContestLevel] [int] NULL,
	[ContestStatus] [int] NULL,
	[OrganizerID] [char](20) NULL,
	[BeignTime] [datetime] NULL,
	[EndTime] [datetime] NULL,
	[AccessPwd] [char](20) NULL,
	[ContestContent] [text] NULL,
	[ContestPath] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[ContestID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[News]    Script Date: 11/28/2011 06:47:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[News](
	[NewsID] [int] IDENTITY(1,1) NOT NULL,
	[ContestID] [int] NULL,
	[Title] [char](50) NULL,
	[Author] [char](30) NULL,
	[VisitTimes] [int] NULL,
	[NewsContent] [text] NULL,
	[NewsType] [char](20) NULL,
	[CreateTime] [datetime] NULL,
	[lastEditTime] [datetime] NULL,
	[NewsPath] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[NewsID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Works]    Script Date: 11/28/2011 06:47:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Works](
	[WorksID] [int] IDENTITY(1,1) NOT NULL,
	[ContestID] [int] NULL,
	[MemberID] [char](20) NULL,
	[Name] [char](30) NULL,
	[WorksType] [char](20) NOT NULL,
	[Awards] [char](20) NULL,
	[WorksPath] [nvarchar](100) NULL,
	[VisitTimes] [int] NULL,
	[CommentTimes] [int] NULL,
	[ScoreTimes] [int] NULL,
	[avgScore] [float] NULL,
	[Summary] [text] NULL,
	[CreativeTime] [datetime] NULL,
	[CreativePlace] [char](30) NULL,
	[LoadTime] [datetime] NULL,
	[Content] [text] NULL,
	[isAllow] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[WorksID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Score]    Script Date: 11/28/2011 06:47:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Score](
	[MemberID] [char](20) NOT NULL,
	[WorksID] [int] NOT NULL,
	[Value] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MemberID] ASC,
	[WorksID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Comment]    Script Date: 11/28/2011 06:47:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Comment](
	[CommentID] [int] IDENTITY(1,1) NOT NULL,
	[WorksID] [int] NULL,
	[MemberID] [char](20) NULL,
	[ReplyMemberID] [char](20) NULL,
	[CommentContent] [text] NULL,
	[CommentTime] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[CommentID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Default [DF__Contest__Contest__10566F31]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[Contest] ADD  DEFAULT ((0)) FOR [ContestLevel]
GO
/****** Object:  Default [DF__Contest__Contest__114A936A]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[Contest] ADD  DEFAULT ((0)) FOR [ContestStatus]
GO
/****** Object:  Default [DF__Contest__AccessP__123EB7A3]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[Contest] ADD  DEFAULT ('admin') FOR [AccessPwd]
GO
/****** Object:  Default [DF__Member__Certifie__014935CB]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[Member] ADD  CONSTRAINT [DF__Member__Certifie__014935CB]  DEFAULT ((0)) FOR [CertifiedStatus]
GO
/****** Object:  Default [DF__Member__Credit__023D5A04]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[Member] ADD  DEFAULT ((0)) FOR [Credit]
GO
/****** Object:  Default [DF__News__VisitTimes__55F4C372]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[News] ADD  DEFAULT ((0)) FOR [VisitTimes]
GO
/****** Object:  Default [DF__Score__Value__15DA3E5D]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[Score] ADD  DEFAULT ((0)) FOR [Value]
GO
/****** Object:  Default [DF__Works__VisitTime__04AFB25B]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[Works] ADD  DEFAULT ((0)) FOR [VisitTimes]
GO
/****** Object:  Default [DF__Works__CommentTi__05A3D694]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[Works] ADD  DEFAULT ((0)) FOR [CommentTimes]
GO
/****** Object:  Default [DF__Works__ScoreTime__0697FACD]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[Works] ADD  DEFAULT ((0)) FOR [ScoreTimes]
GO
/****** Object:  Default [DF__Works__avgScore__078C1F06]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[Works] ADD  DEFAULT ((0.0)) FOR [avgScore]
GO
/****** Object:  Default [DF__Works__isAllow__0880433F]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[Works] ADD  DEFAULT ((0)) FOR [isAllow]
GO
/****** Object:  ForeignKey [FK__Comment__MemberI__10216507]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD FOREIGN KEY([MemberID])
REFERENCES [dbo].[Member] ([MemberID])
GO
/****** Object:  ForeignKey [FK__Comment__ReplyMe__11158940]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD FOREIGN KEY([ReplyMemberID])
REFERENCES [dbo].[Member] ([MemberID])
GO
/****** Object:  ForeignKey [FK__Comment__WorksID__0F2D40CE]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD FOREIGN KEY([WorksID])
REFERENCES [dbo].[Works] ([WorksID])
GO
/****** Object:  ForeignKey [FK__Contest__AccessP__1332DBDC]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[Contest]  WITH CHECK ADD FOREIGN KEY([OrganizerID])
REFERENCES [dbo].[Member] ([MemberID])
GO
/****** Object:  ForeignKey [FK__News__ContestID__56E8E7AB]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[News]  WITH CHECK ADD FOREIGN KEY([ContestID])
REFERENCES [dbo].[Contest] ([ContestID])
GO
/****** Object:  ForeignKey [FK__Score__MemberID__17C286CF]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[Score]  WITH CHECK ADD FOREIGN KEY([MemberID])
REFERENCES [dbo].[Member] ([MemberID])
GO
/****** Object:  ForeignKey [FK__Score__WorksID__16CE6296]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[Score]  WITH CHECK ADD FOREIGN KEY([WorksID])
REFERENCES [dbo].[Works] ([WorksID])
GO
/****** Object:  ForeignKey [FK__Works__ContestID__09746778]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[Works]  WITH CHECK ADD FOREIGN KEY([ContestID])
REFERENCES [dbo].[Contest] ([ContestID])
GO
/****** Object:  ForeignKey [FK__Works__MemberID__0A688BB1]    Script Date: 11/28/2011 06:47:42 ******/
ALTER TABLE [dbo].[Works]  WITH CHECK ADD FOREIGN KEY([MemberID])
REFERENCES [dbo].[Member] ([MemberID])
GO
