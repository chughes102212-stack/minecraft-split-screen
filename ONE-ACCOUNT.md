# One Microsoft account

The mod uses the single Microsoft account already authenticated by the Minecraft client. Both local player slots are local views/inputs in that one session; the mod never asks for a second login and never copies, stores, or exposes Microsoft credentials or access tokens.

This supports single-player worlds. Online multiplayer, Realms, account-owned cosmetics, achievements, and other account services remain associated with the one signed-in account and are not turned into separate online identities. The two local slots must not be represented as two server-side players without a server protocol designed for that behavior.

`SingleAccountSession` captures only the session username and UUID for local coordination. It intentionally does not create a replacement `Session` or access-token object.
