#!/bin/bash

set -e

if [[ -z ${GPG_PUBLIC_KEY} ]]; then
    echo "Error: GPG_PUBLIC_KEY is unset!" >&2
    exit 1;
fi

if [[ -z ${GPG_PRIVATE_KEY} ]]; then
    echo "Error: GPG_PRIVATE_KEY is unset!" >&2
    exit 1;
fi

if [[ -z ${GPG_PASSPHRASE} ]]; then
    echo "Error: GPG_PASSPHRASE is unset!" >&2
    exit 1;
fi

echo '-----BEGIN PGP PUBLIC KEY BLOCK-----
Version: GnuPG v2
' >public.asc
echo ${GPG_PUBLIC_KEY} >>public.asc
echo '-----END PGP PUBLIC KEY BLOCK-----' >>public.asc

echo '-----BEGIN PGP PRIVATE KEY BLOCK-----
Version: GnuPG v2
' >private.asc
echo ${GPG_PRIVATE_KEY} >>private.asc
echo '-----END PGP PRIVATE KEY BLOCK-----' >>private.asc

export HOME=$(pwd)
gpg --batch --import public.asc
echo -n "${GPG_PASSPHRASE}" | gpg --passphrase-fd 0 --batch --import private.asc
rm public.asc
rm private.asc

mvn deploy
rm -rf .gnupg
