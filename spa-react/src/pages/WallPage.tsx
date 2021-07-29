import React from "react";
import "./../styles/base.scss";
import "./../styles/feed.scss";

interface PostProps {
  name: String;
  body: String;
}

function Post(props: PostProps) {
  return (
    <div className="feed-item">
      <div>{props.name}</div>
      <div>{props.body}</div>
    </div>
  );
}

const posts: Array<PostProps> = [
  {
    name: "David Lukens",
    body: "Whether you're coming in fresh to using Typescript with React or are a grizzled veteran looking to add more functional components to your codebase by introducing hooks, it's important to know the different ways to define props",
  },
  {
    name: "David Lukens",
    body: "Accomplishing the same functionality using the non-destructured syntax would look something like this:    ",
  },
  {
    name: "David Lukens",
    body: "You can read more about the benefits of React.FC here",
  },
];

export default function WallPage() {
  return (
    <div id="feed-page">
      <div>
        {posts.map((post) => (
          <Post name={post.name} body={post.body} />
        ))}
      </div>
    </div>
  );
}
